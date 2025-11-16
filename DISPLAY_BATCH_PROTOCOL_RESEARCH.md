# Display Batch Protocol Research

## Critical Discovery: DisplayDrawImage (0x88) Does NOT Exist in Python Implementation

### Executive Summary
After researching the Python reference implementation (v2.3), we discovered a **critical architectural mismatch**:

1. **DisplayDrawImage (0x88) Protocol Class**: ✅ Exists in protocol definitions but **NEVER USED**
2. **Python Implementation**: ✅ Uses **only individual DisplayDrawPoint (0x82) commands**
3. **Java Implementation**: ❌ Attempted to use non-existent batch DisplayDrawImage commands

### Evidence from Python Reference Implementation

#### File: `/reference/python-venv/lib/python3.12/site-packages/codrone_edu/protocol.py`
```python
DisplayDrawImage            = 0x88      # 그림 그리기
```
- The protocol constant **IS defined** in Python
- **BUT IT IS NEVER USED ANYWHERE IN THE CODEBASE**

#### File: `/reference/python-venv/lib/python3.12/site-packages/codrone_edu/drone.py`

**Method: `controller_draw_canvas(image)` (Line 6602)**
```python
def controller_draw_canvas(self, image):
    """Draws custom image canvas onto the controller screen"""
    if not isinstance(image, PIL.Image.Image):
        raise Exception("Unable to draw image canvas. Use controller_create_canvas()")
    
    if sys.platform != 'emscripten':
        return self._controller_draw_canvas_desktop(image)
    else:
        return asyncio.create_task(self._controller_draw_canvas_emscripten(image))
```

**Method: `_controller_draw_canvas_desktop(image)` (Line 6619)**
```python
def _controller_draw_canvas_desktop(self, image):
    img = list(image.getdata())
    self.controller_draw_image(img)
```

**Method: `controller_draw_image(pixel_list)` (Line 7275)**
```python
def controller_draw_image(self, pixel_list):
    """draws image when given a pixel_list of image data"""
    if sys.platform != 'emscripten':
        return self._controller_draw_image_desktop(pixel_list)
```

**Method: `_controller_draw_image_desktop(pixel_list)` (Line 7287)**
```python
def _controller_draw_image_desktop(self, pixel_list):
    self.controller_clear_screen()  # Clear first
    
    # Iterate through ALL 8192 pixels (128 × 64)
    for k in range(64):              # rows
        for i in range(128):         # columns
            if (127 * k) + i == 8001:
                return               # end
            else:
                current_index = pixel_list[(127 * k) + i]
            
            # Check pixel color
            if num_elem == 4:  # RGBA
                if current_index[0] > 200 and current_index[1] > 200 and current_index[2] > 200 and current_index[3] > 200:
                    None  # White pixel, skip
                elif current_index[0] == 0 and current_index[1] == 0 and current_index[2] == 0 and current_index[3] == 0:
                    None  # Black pixel with 0 alpha, skip
                else:
                    # DRAW PIXEL INDIVIDUALLY
                    self.sendDisplayDrawPoint(i, k, DisplayPixel.Black)
                    self.sendDisplayDrawPoint(i, k, DisplayPixel.Black)  # Note: called twice!
                    time.sleep(0.001)  # 1ms delay between each pixel!
```

### The Python Algorithm (Actual Implementation)

1. **Clear entire screen** (DisplayClearAll)
2. **For each pixel (8192 total for 128×64):**
   - Check pixel color from image
   - If pixel is black: Send DisplayDrawPoint (0x82) command
   - If pixel is white: Skip (already cleared)
   - **Wait 1ms after each point**

### Performance Implications

**Actual Python Performance:**
- For a completely black screen: ~8192 pixels × 1ms = **~8.2 seconds**
- For partially filled screen: 1-4 seconds
- **This is EXTREMELY SLOW** but it works

**What We Attempted (DisplayDrawImage Batch):**
- Batch 1024 bytes in 8 messages: **~40ms total** (200x faster!)
- But: **This protocol command doesn't actually exist in the firmware**

### Why DisplayDrawImage (0x88) Exists But Isn't Used

Hypotheses:
1. **Firmware limitation**: The firmware may not support batch image commands
2. **Development placeholder**: Protocol was defined but never implemented
3. **Hardware limitation**: LCD controller only supports individual draw commands
4. **Future feature**: Reserved for future firmware versions

### The Root Cause of Our Serial Port Closure

Our implementation was sending **DisplayDrawImage (0x88)** commands, which the firmware doesn't recognize or support. The firmware's response was to close the serial port connection when receiving these unknown commands.

**Evidence:**
- Individual DisplayDrawPoint commands work fine
- Batch DisplayDrawImage commands cause immediate port closure
- Python implementation only uses DisplayDrawPoint (which works)

### Current Status

**Java Implementation Issues:**
- ✅ DisplayDrawImage protocol class exists and is well-designed
- ✅ DisplayDrawImage is registered with command ID 0x88
- ❌ **But this command appears to be unsupported by firmware**
- ❌ Chunking strategy fails because the underlying command isn't supported

**Options Forward:**

1. **Option A: Fall Back to Python Implementation**
   - Use individual DisplayDrawPoint calls for each pixel
   - Very slow (~1-8 seconds) but functionally correct
   - Matches Python API behavior exactly
   - **Recommended for API compatibility**

2. **Option B: Research Firmware Capabilities**
   - Contact Robolink to confirm if 0x88 is supported
   - Ask if newer firmware versions support batch image commands
   - Determine if 0x88 is a genuine protocol or placeholder

3. **Option C: Hybrid Approach**
   - Attempt DisplayDrawImage first
   - Fall back to DisplayDrawPoint if port closes
   - User gets performance if firmware supports it, compatibility otherwise

4. **Option D: Investigate Timeout/Reconnection**
   - Reason: Python sends 8192+ individual commands
   - Maybe port closure is a timeout, not a protocol error
   - May need to investigate port keep-alive or reconnection logic

### Verification Needed

1. **Is DisplayDrawImage actually supported?**
   - No evidence in Python code
   - No mention in documentation
   - No Robolink confirmation

2. **Why is Python so slow?**
   - Iterates all 8192 pixels individually
   - 1ms delay per pixel (intentional or firmware requirement?)
   - Clear-then-draw pattern instead of batch update

3. **Is there another batch protocol?**
   - Check if 0x87 (DisplayDrawStringAlign) has batch mode
   - Check firmware documentation for undocumented commands

### Files to Review for More Evidence

- Python `receiver.py`: Check how responses to 0x88 are handled
- Python `protocol.py`: Full protocol definition
- Firmware documentation (if available)
- CoDrone EDU controller specifications

### Recommendation

**For immediate resolution:**
- Implement `controllerDrawCanvas()` using individual `controller_draw_point()` calls
- Match Python behavior exactly
- Accept the ~1-8 second display update time
- Document the limitation for users
- Flag as "Matches Python API" even though it's slow

**For long-term investigation:**
- Research DisplayDrawImage support with Robolink
- Test if newer firmware versions support batch mode
- Consider providing both implementations (fast/experimental and slow/compatible)

---

## Code Search Results

### Grep: Python doesn't use 0x88 anywhere
```
$ grep -r "0x88\|DisplayDrawImage" codrone_edu/*.py
protocol.py: DisplayDrawImage = 0x88
system.py: BlackBlack = 0x88  # (unrelated)
```
- Only defined in protocol, never used in any method

### Java: We implemented what Python never uses
- DisplayDrawImage.java: ✅ Full implementation
- Drone.java: ✅ Chunking strategy
- But: **Firmware doesn't support the underlying command**

---

## Critical Update: Vertical Lines Prove Data Transmission Works

**Your observation is correct:** The vertical lines on the display prove:
- ✅ **Data IS reaching the LCD controller**
- ✅ **Byte format IS correct** (page-aligned 8-pixel rows)
- ✅ **DisplayDrawImage (0x88) IS being accepted by firmware**

**New Hypothesis (LCD Driver Architecture):**
The CoDrone EDU uses a standard monochrome LCD driver (likely ST7565/ST7567 or SSD1306):
- Updates work in **8-pixel-high pages**
- Each page needs **page select command + column address + data bytes**
- The firmware internally converts DisplayDrawImage commands to LCD page commands
- **Vertical lines = correct byte packing, but incomplete image transmission**

**Why Port Closes After First Message:**
Possible causes:
1. **Timing issue:** Firmware expects acknowledgments between chunks
2. **Buffer overflow:** LCD driver buffer fills before second message arrives
3. **Rate limiting:** Firmware has minimum time between DisplayDrawImage commands
4. **Missing handshake:** Protocol may require waiting for display ready signal
5. **Message accumulation:** Too many messages queued, causing reset

**The Real Issue:** Not that DisplayDrawImage doesn't work, but **how the firmware sequences multi-chunk updates**.

**Solution Path:**
1. ✅ Implement individual DisplayDrawPoint like Python (proven working)
2. Then investigate why DisplayDrawImage chunking fails:
   - Add longer delays between chunks (test 50ms, 100ms, 200ms)
   - Implement acknowledgment waiting between chunks
   - Send fewer chunks (2-4 instead of 8)
   - Try sending full height in fewer wide messages instead of tall narrow ones

**Evidence Supporting DisplayDrawImage:**
- Python defines DisplayImage class in protocol (never used, but defined)
- Command 0x88 is officially registered in DataType enum
- Vertical lines prove firmware accepts the message format
- No error response—just silent port closure (suggests buffer/timing issue)
