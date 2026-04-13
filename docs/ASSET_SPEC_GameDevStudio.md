# GAME DEV STUDIO — Asset Specification
### Complete production guide for all visual and audio assets
**Tool:** Aseprite (pixel art) / Compose Canvas (placeholders)
**Palette:** Endesga 32 (32 colors) — or custom 32-color palette
**Rendering:** `image-rendering: pixelated` / Compose `FilterQuality.None`
**Export:** PNG (sprites), OGG (audio), JSON (sprite atlases)

---

## 0. Placeholder Strategy (Phases 1-3)

During early development, **no final PNG assets are needed**. Instead, all visuals are drawn programmatically in **Compose Canvas**. This produces pixel-art-quality placeholders that look good enough to playtest and even ship as a soft launch MVP.

### Why Compose Canvas instead of PNGs
- Zero external asset files to manage
- Instant iteration — change a color and recompile
- Claude Code can generate and modify these directly
- Looks way better than rectangles or emoji
- Easy to swap out for real spritesheets later (same dimensions)

### Pixel Rendering Setup
```kotlin
// CRITICAL: disable anti-aliasing everywhere for pixel art look
val pixelPaint = Paint().apply {
    isAntiAlias = false
    filterQuality = FilterQuality.None
    style = PaintingStyle.Fill
}

// In Canvas composable, disable built-in AA:
Canvas(modifier = Modifier.size(width.dp, height.dp)) {
    drawContext.canvas.nativeCanvas.apply {
        // Disable AA on the native canvas too
        drawFilter = PaintFlagsDrawFilter(
            Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG, 0
        )
    }
    // All drawing here uses integer coordinates only
}

// Scale factor: each game pixel = SCALE screen dp
const val PIXEL_SCALE = 3 // 1 game pixel = 3dp on screen
// Adjust based on screen density for consistent look
```

### Color Palette (Kotlin object)
```kotlin
object Palette {
    // Skin tones
    val SkinLight  = Color(0xFFEAD4AA)
    val SkinMedium = Color(0xFFC28569)
    val SkinTan    = Color(0xFFE4A672)
    val SkinDark   = Color(0xFFB86F50)

    // Hair
    val HairBlack  = Color(0xFF3A4466)
    val HairBrown  = Color(0xFF733E39)
    val HairBlonde = Color(0xFFE4A672)
    val HairRed    = Color(0xFFA22633)

    // Shirts / roles
    val ShirtProgrammer = Color(0xFF124E89)  // blue
    val ShirtArtist     = Color(0xFFA22633)  // red
    val ShirtSound      = Color(0xFF3E8948)  // green
    val ShirtDesigner   = Color(0xFFB55088)  // pink
    val ShirtWriter     = Color(0xFF68386C)  // purple
    val ShirtMarketer   = Color(0xFFF77622)  // orange
    val ShirtProducer   = Color(0xFF5A6988)  // gray

    // Office
    val Floor       = Color(0xFFC28569)
    val FloorDark   = Color(0xFFA06A50)
    val Wall        = Color(0xFFE8E0D0)
    val WallSide    = Color(0xFFC8B8A0)
    val DeskTop     = Color(0xFFD0C0A0)
    val DeskSide    = Color(0xFFA89870)
    val Monitor     = Color(0xFF282830)
    val Screen      = Color(0xFF40A060)
    val Chair       = Color(0xFF484858)

    // Decorations
    val PlantPot   = Color(0xFFB86F50)
    val PlantGreen = Color(0xFF3E8948)
    val LeafGreen  = Color(0xFF63C74D)
    val Shelf      = Color(0xFFA08060)
    val BookBlue   = Color(0xFF124E89)
    val BookRed    = Color(0xFFA22633)

    // Effects
    val Fire1  = Color(0xFFE43B44)
    val Fire2  = Color(0xFFF77622)
    val Fire3  = Color(0xFFFEAE34)
    val Spark  = Color(0xFFFEE761)
    val CodeGreen = Color(0xFF39FF14)
    val CodeCyan  = Color(0xFF00E5FF)

    // UI
    val BgDeep    = Color(0xFF0A0A12)
    val BgPanel   = Color(0xFF14151F)
    val Border    = Color(0xFF2A2B3D)
    val TextLight = Color(0xFFD4D4E8)
    val TextMuted = Color(0xFF5A6988)
    val AccentGreen = Color(0xFF39FF14)
    val AccentGold  = Color(0xFFFFD700)
}
```

---

### 0.1 Staff Sprites (Compose Canvas)

Each staff member is drawn as a **6×10 game pixel** character (rendered at PIXEL_SCALE).

```kotlin
// Staff character: 6px wide × 10px tall
// Scale on screen: 6×3 = 18dp wide, 10×3 = 30dp tall

fun DrawScope.drawStaffSprite(
    x: Float,           // top-left X in game pixels
    y: Float,           // top-left Y in game pixels
    skin: Color,
    hair: Color,
    shirt: Color,
    isWorking: Boolean,
    animFrame: Int       // 0 or 1 for idle bounce
) {
    val s = PIXEL_SCALE.toFloat()
    val bounce = if (animFrame == 1) -s else 0f

    // Shadow (on ground)
    pixel(x + 0, y + 10, Color(0x26000000), s)
    pixel(x + 1, y + 10, Color(0x26000000), s)
    pixel(x + 2, y + 10, Color(0x26000000), s)
    pixel(x + 3, y + 10, Color(0x26000000), s)
    pixel(x + 4, y + 10, Color(0x26000000), s)
    pixel(x + 5, y + 10, Color(0x26000000), s)

    // Hair (top of head, 2px tall)
    pixel(x + 1, y + 0 + bounce, hair, s)
    pixel(x + 2, y + 0 + bounce, hair, s)
    pixel(x + 3, y + 0 + bounce, hair, s)
    pixel(x + 4, y + 0 + bounce, hair, s)
    pixel(x + 0, y + 1 + bounce, hair, s)  // side hair
    pixel(x + 1, y + 1 + bounce, hair, s)
    pixel(x + 2, y + 1 + bounce, hair, s)
    pixel(x + 3, y + 1 + bounce, hair, s)
    pixel(x + 4, y + 1 + bounce, hair, s)

    // Face (2px tall)
    pixel(x + 1, y + 2 + bounce, skin, s)
    pixel(x + 2, y + 2 + bounce, skin, s)
    pixel(x + 3, y + 2 + bounce, skin, s)
    pixel(x + 4, y + 2 + bounce, skin, s)
    pixel(x + 1, y + 3 + bounce, skin, s)
    pixel(x + 2, y + 3 + bounce, skin, s)  // eye left
    pixel(x + 3, y + 3 + bounce, skin, s)
    pixel(x + 4, y + 3 + bounce, skin, s)  // eye right

    // Eyes (on face row 3)
    pixel(x + 2, y + 3 + bounce, Color(0xFF181425), s)
    pixel(x + 4, y + 3 + bounce, Color(0xFF181425), s)

    // Body / shirt (4px tall)
    for (row in 4..7) {
        pixel(x + 1, y + row + bounce, shirt, s)
        pixel(x + 2, y + row + bounce, shirt, s)
        pixel(x + 3, y + row + bounce, shirt, s)
        pixel(x + 4, y + row + bounce, shirt, s)
    }

    // Arms
    pixel(x + 0, y + 5 + bounce, shirt, s)
    pixel(x + 5, y + 5 + bounce, shirt, s)
    pixel(x + 0, y + 6 + bounce, skin, s)  // hand
    pixel(x + 5, y + 6 + bounce, skin, s)  // hand

    // Legs (2px)
    pixel(x + 2, y + 8 + bounce, Color(0xFF3A4466), s)
    pixel(x + 3, y + 8 + bounce, Color(0xFF3A4466), s)
    pixel(x + 2, y + 9 + bounce, Color(0xFF262B44), s)  // shoes
    pixel(x + 3, y + 9 + bounce, Color(0xFF262B44), s)
}

// Helper to draw a single game pixel
private fun DrawScope.pixel(gx: Float, gy: Float, color: Color, scale: Float) {
    drawRect(
        color = color,
        topLeft = Offset(gx * scale, gy * scale),
        size = Size(scale, scale)
    )
}
```

#### Character Variants
Generate 20 unique characters by combining:

```kotlin
data class StaffAppearance(
    val skin: Color,
    val hair: Color,
    val shirt: Color  // determined by role
)

// Pre-generated pool of 20 appearances
val STAFF_APPEARANCES = listOf(
    StaffAppearance(Palette.SkinLight, Palette.HairBlack, Palette.ShirtProgrammer),
    StaffAppearance(Palette.SkinMedium, Palette.HairBrown, Palette.ShirtArtist),
    StaffAppearance(Palette.SkinDark, Palette.HairBlack, Palette.ShirtSound),
    StaffAppearance(Palette.SkinTan, Palette.HairBlonde, Palette.ShirtDesigner),
    // ... 16 more combinations
)
```

#### Working Effects (drawn above the character)

```kotlin
fun DrawScope.drawWorkingEffect(
    x: Float, y: Float,  // character position
    role: StaffRole,
    animFrame: Int        // 0-3 cycling
) {
    val s = PIXEL_SCALE.toFloat()
    when (role) {
        PROGRAMMER -> {
            // Floating 0s and 1s in green/cyan
            val chars = listOf(
                Triple(x + 6, y - 2 - animFrame, Palette.CodeGreen),
                Triple(x + 8, y - 4 - (animFrame + 1) % 4, Palette.CodeCyan),
            )
            chars.forEach { (fx, fy, color) ->
                pixel(fx, fy, color, s)
            }
        }
        ARTIST -> {
            // Colorful dots
            val dots = listOf(Palette.Fire1, Palette.Fire3, Palette.CodeCyan)
            dots.forEachIndexed { i, color ->
                pixel(x + 5 + i * 2, y - 1 - (animFrame + i) % 3, color, s)
            }
        }
        SOUND_DESIGNER -> {
            // Musical notes
            pixel(x + 6, y - 2 - animFrame, Palette.AccentGold, s)
            pixel(x + 7, y - 3 - animFrame, Palette.AccentGold, s)
            pixel(x + 8, y - 2 - (animFrame + 2) % 4, Palette.Spark, s)
        }
        GAME_DESIGNER -> {
            // Lightbulb
            if (animFrame % 2 == 0) {
                pixel(x + 3, y - 3, Palette.Spark, s)
                pixel(x + 2, y - 2, Palette.Spark, s)
                pixel(x + 3, y - 2, Palette.AccentGold, s)
                pixel(x + 4, y - 2, Palette.Spark, s)
                pixel(x + 3, y - 1, Palette.AccentGold, s)
            }
        }
        WRITER -> {
            // Text squiggles
            pixel(x + 6, y - 1 - animFrame % 2, Palette.TextLight, s)
            pixel(x + 7, y - 2 - animFrame % 2, Palette.TextLight, s)
            pixel(x + 8, y - 1 - (animFrame + 1) % 2, Palette.TextLight, s)
        }
        MARKETER -> {
            // Megaphone waves
            pixel(x + 6, y - 1, Palette.Fire2, s)
            pixel(x + 7, y - 2 - animFrame % 2, Palette.Fire2, s)
            pixel(x + 8, y - 1, Palette.Fire3, s)
        }
        PRODUCER -> {
            // Clipboard check
            pixel(x + 6, y - 2, Palette.TextLight, s)
            pixel(x + 7, y - 2, Palette.TextLight, s)
            pixel(x + 6, y - 1, Palette.TextLight, s)
            if (animFrame % 2 == 0) pixel(x + 7, y - 1, Palette.AccentGreen, s) // checkmark
        }
    }
}
```

#### Fire Effect (Crunch / Inspiration)
```kotlin
fun DrawScope.drawFireEffect(
    x: Float, y: Float,  // character position
    animFrame: Int
) {
    val s = PIXEL_SCALE.toFloat()
    val colors = listOf(Palette.Fire1, Palette.Fire2, Palette.Fire3)
    val rng = Random(animFrame * 7 + (x * 13).toInt())

    // 6-8 fire pixels around the character
    repeat(7) {
        val fx = x - 1 + rng.nextInt(8)
        val fy = y - 2 + rng.nextInt(5)
        pixel(fx, fy, colors[rng.nextInt(3)], s)
    }
}
```

#### Stat Popup Numbers
```kotlin
fun DrawScope.drawStatPopup(
    x: Float, y: Float,
    value: Int,
    color: Color,
    animProgress: Float  // 0.0 to 1.0, floats upward then fades
) {
    val s = PIXEL_SCALE.toFloat()
    val offsetY = y - (animProgress * 8)
    val alpha = (1f - animProgress).coerceIn(0f, 1f)

    // Draw "+N" using mini pixel font or drawText
    drawContext.canvas.nativeCanvas.drawText(
        "+$value",
        x * s, offsetY * s,
        android.graphics.Paint().apply {
            this.color = color.copy(alpha = alpha).toArgb()
            textSize = 5 * s
            typeface = Typeface.MONOSPACE
            isAntiAlias = false
        }
    )
}
```

---

### 0.2 Furniture (Compose Canvas)

All furniture is drawn as simple pixel blocks. No iso perspective needed for MVP — just rectangles with a highlight/shadow edge for minimal depth.

```kotlin
// Desk: 18×9 game pixels (top surface + front face)
fun DrawScope.drawDesk(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    // Top surface
    fillRect(x, y, 18, 5, Palette.DeskTop, s)
    // Front face (darker)
    fillRect(x, y + 5, 18, 4, Palette.DeskSide, s)
    // Highlight edge
    fillRect(x, y, 18, 1, Palette.DeskTop.lighten(0.15f), s)
}

// Monitor: 8×6 game pixels on top of desk
fun DrawScope.drawMonitor(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    // Casing
    fillRect(x, y, 8, 6, Palette.Monitor, s)
    // Screen (inset 1px)
    fillRect(x + 1, y + 1, 6, 4, Palette.Screen, s)
    // Stand
    fillRect(x + 3, y + 6, 2, 1, Palette.Monitor, s)
}

// Chair: 6×6 game pixels
fun DrawScope.drawChair(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    fillRect(x, y, 6, 4, Palette.Chair, s)
    fillRect(x + 1, y + 4, 1, 2, Palette.Chair, s) // leg
    fillRect(x + 4, y + 4, 1, 2, Palette.Chair, s) // leg
}

// Plant: 6×10 game pixels
fun DrawScope.drawPlant(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    // Pot
    fillRect(x + 1, y + 6, 4, 4, Palette.PlantPot, s)
    // Leaves
    fillRect(x + 0, y + 2, 3, 5, Palette.PlantGreen, s)
    fillRect(x + 3, y + 0, 3, 6, Palette.LeafGreen, s)
    fillRect(x + 1, y + 1, 2, 2, Palette.LeafGreen, s)
}

// Bookshelf: 12×16 game pixels
fun DrawScope.drawBookshelf(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    fillRect(x, y, 12, 16, Palette.Shelf, s)
    // Shelves (horizontal lines)
    fillRect(x, y + 5, 12, 1, Palette.DeskSide, s)
    fillRect(x, y + 10, 12, 1, Palette.DeskSide, s)
    // Books (colored blocks on shelves)
    fillRect(x + 1, y + 1, 10, 4, Palette.BookBlue, s)
    fillRect(x + 1, y + 6, 8, 4, Palette.BookRed, s)
    fillRect(x + 1, y + 11, 10, 4, Palette.DeskTop, s)
}

// Vending machine: 8×14 game pixels
fun DrawScope.drawVendingMachine(x: Float, y: Float) {
    val s = PIXEL_SCALE.toFloat()
    fillRect(x, y, 8, 14, Palette.Fire1, s)
    fillRect(x + 1, y + 2, 6, 6, Palette.Fire1.lighten(0.2f), s)
    // Colorful items inside
    val itemColors = listOf(Palette.AccentGreen, Palette.Spark, Palette.CodeCyan, Palette.Fire2)
    for (row in 0..2) for (col in 0..2) {
        pixel(x + 2 + col * 2, y + 3 + row * 2, itemColors[(row + col) % 4], s)
    }
}

// Helper
private fun DrawScope.fillRect(gx: Float, gy: Float, w: Int, h: Int, color: Color, scale: Float) {
    drawRect(color, Offset(gx * scale, gy * scale), Size(w * scale, h * scale))
}
```

---

### 0.3 Particle Effects (Compose Canvas)

```kotlin
// Confetti particle
data class ConfettiParticle(
    var x: Float, var y: Float,
    val color: Color,
    var vy: Float = -2f,
    var vx: Float = (-1..1).random().toFloat(),
    var life: Float = 1f
)

fun DrawScope.drawConfetti(particles: List<ConfettiParticle>) {
    val s = PIXEL_SCALE.toFloat()
    particles.forEach { p ->
        if (p.life > 0) {
            pixel(p.x, p.y, p.color.copy(alpha = p.life), s)
            pixel(p.x + 1, p.y, p.color.copy(alpha = p.life), s)
        }
    }
}

// Coin collect effect
fun DrawScope.drawCoinEffect(x: Float, y: Float, animFrame: Int) {
    val s = PIXEL_SCALE.toFloat()
    val floatY = y - animFrame * 0.5f
    pixel(x, floatY, Palette.AccentGold, s)
    pixel(x + 1, floatY, Palette.Spark, s)
    pixel(x, floatY + 1, Palette.Spark, s)
    pixel(x + 1, floatY + 1, Palette.AccentGold, s)
}

// Speech bubble with text
fun DrawScope.drawSpeechBubble(x: Float, y: Float, text: String) {
    val s = PIXEL_SCALE.toFloat()
    val w = text.length * 4 + 4
    // Bubble background
    fillRect(x, y, w, 7, Color.White, s)
    // Tail
    pixel(x + 3, y + 7, Color.White, s)
    pixel(x + 2, y + 8, Color.White, s)
    // Text (using native canvas for tiny text)
    drawContext.canvas.nativeCanvas.drawText(
        text, (x + 2) * s, (y + 5) * s,
        android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            textSize = 4 * s
            typeface = Typeface.MONOSPACE
            isAntiAlias = false
        }
    )
}
```

---

### 0.4 Studio View Composable (Full Assembly)

```kotlin
@Composable
fun StudioView(
    staff: List<StaffWithAppearance>,
    animFrame: Int,  // ticks 0-3, updated every 300ms
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxWidth().aspectRatio(320f / 256f)) {
        val s = PIXEL_SCALE.toFloat()

        // 1. Draw floor (checkerboard)
        for (y in 0 until 128 step 2) {
            for (x in 0 until 160 step 2) {
                val checker = ((x + y) / 2) % 4 < 2
                fillRect(x.toFloat(), y.toFloat(), 2, 2,
                    if (checker) Palette.Floor else Palette.FloorDark, s)
            }
        }

        // 2. Draw walls (back wall + windows)
        fillRect(0f, 0f, 160, 8, Palette.Wall, s)
        // Windows
        fillRect(20f, 1f, 18, 6, Color(0xFFA8D8E8), s)
        fillRect(60f, 1f, 18, 6, Color(0xFFA8D8E8), s)
        fillRect(100f, 1f, 18, 6, Color(0xFFA8D8E8), s)

        // 3. Draw furniture at fixed positions
        // Row 1 desks
        drawDesk(10f, 20f); drawMonitor(13f, 14f)
        drawDesk(45f, 20f); drawMonitor(48f, 14f)
        drawDesk(80f, 20f); drawMonitor(83f, 14f)
        drawDesk(115f, 20f); drawMonitor(118f, 14f)

        // Row 2 desks
        drawDesk(20f, 55f); drawMonitor(23f, 49f)
        drawDesk(60f, 55f); drawMonitor(63f, 49f)
        drawDesk(100f, 55f); drawMonitor(103f, 49f)

        // Decorations
        drawPlant(145f, 30f)
        drawBookshelf(142f, 55f)
        drawVendingMachine(2f, 40f)

        // 4. Draw staff at their desk positions
        val deskPositions = listOf(
            15f to 10f, 50f to 10f, 85f to 10f, 120f to 10f,
            25f to 45f, 65f to 45f, 105f to 45f,
            // Row 3 if > 7 staff...
        )

        staff.forEachIndexed { i, staffMember ->
            if (i < deskPositions.size) {
                val (dx, dy) = deskPositions[i]
                drawStaffSprite(
                    x = dx, y = dy,
                    skin = staffMember.appearance.skin,
                    hair = staffMember.appearance.hair,
                    shirt = staffMember.appearance.shirt,
                    isWorking = staffMember.isWorking,
                    animFrame = (animFrame + i) % 2  // offset so they don't all bounce in sync
                )
                if (staffMember.isWorking) {
                    drawWorkingEffect(dx, dy, staffMember.role, animFrame)
                }
                if (staffMember.isCrunching) {
                    drawFireEffect(dx, dy, animFrame)
                }
            }
        }
    }
}
```

### 0.5 Animation Loop
```kotlin
// In GameViewModel or a dedicated AnimationManager:
private val _animFrame = MutableStateFlow(0)
val animFrame: StateFlow<Int> = _animFrame

init {
    viewModelScope.launch {
        while (true) {
            delay(300) // 300ms per frame = ~3.3 FPS pixel animation
            _animFrame.value = (_animFrame.value + 1) % 4
        }
    }
}
```

### 0.6 Transition to Real Assets (Phase 4+)

When switching from Canvas to PNG spritesheets:

1. **Keep the same dimensions** — sprites stay 6×10 game pixels (or scale up to 16×16/24×32 for more detail)
2. **Create a SpriteRenderer interface** so swapping Canvas → Bitmap is a single change
3. **Export the palette** from Compose to Aseprite format (.gpl) for consistency

```kotlin
// Abstraction to make the swap painless:
interface StaffRenderer {
    fun DrawScope.renderStaff(x: Float, y: Float, staff: StaffWithAppearance, animFrame: Int)
}

class CanvasStaffRenderer : StaffRenderer { /* current Canvas code */ }
class SpriteStaffRenderer(private val atlas: ImageBitmap) : StaffRenderer { /* future PNG code */ }
```

### When to Switch
- **Phase 1-3:** Use all Canvas code above. It's good enough.
- **Phase 4:** Start creating real sprites in Aseprite. Swap StaffRenderer.
- **Phase 6:** All final assets in place. Remove Canvas renderers.

---

## 1. Global Specifications

### Base Resolution
```
Logical resolution:    320 × 568 px (portrait, 16:9-ish)
Render scale:          Integer scaling to device resolution
Pixel density:         1 game pixel = N screen pixels (auto-calculated)
Orientation:           Portrait only
```

### Color Palette — Endesga 32
```
#be4a2f  #d77643  #ead4aa  #e4a672  #b86f50  #733e39  #3e2731  #a22633
#e43b44  #f77622  #feae34  #fee761  #63c74d  #3e8948  #265c42  #193c3e
#124e89  #0099db  #2ce8f5  #ffffff  #c0cbdc  #8b9bb4  #5a6988  #3a4466
#262b44  #181425  #ff0044  #68386c  #b55088  #f6757a  #e8b796  #c28569
```

### Pixel Grid Rules
- **No sub-pixel rendering** — every element snaps to the pixel grid
- **No anti-aliasing** — hard edges only
- **No rotation** — only 90° increments if needed
- **Consistent outline:** 1px dark outline on all sprites (color from palette)
- **Shading:** max 3 tones per color (highlight, base, shadow)

---

## 2. Staff Sprites

### Dimensions & Frames
```
Sprite size:     16 × 16 px (body fits in ~10×14, 1px padding each side)
Spritesheet:     Horizontal strip, 1 row per character variant
Frame size:      16 × 16 px per frame
```

### Required Variants
Each staff role needs a **base sprite** in multiple skin/hair color combos. Minimum 20 unique staff appearances for the candidate pool to feel varied.

| Variant | Count | Notes |
|---------|-------|-------|
| Skin tones | 4 | Light, medium, tan, dark |
| Hair colors | 4 | Black, brown, blonde, red |
| Hair styles | 3 | Short, long, bald/hat |
| Total combos | ~20 | Not all combinations needed, curate the best |

### Animation States
Each character has these animation states:

| State | Frames | Speed | Description |
|-------|--------|-------|-------------|
| Idle | 2 | 500ms/frame | Slight bounce or blink |
| Working | 4 | 250ms/frame | Typing, drawing, or playing instrument |
| Walking | 4 | 200ms/frame | 4-direction (only down + right needed, flip for left) |
| Celebrating | 3 | 300ms/frame | Arms up, jump |
| Sad | 2 | 600ms/frame | Slouched, slow |
| Sleeping | 2 | 800ms/frame | Head on desk (fatigue > 80) |
| Sick | 2 | 600ms/frame | Green face, wobbly |

### Role-Specific Working Animations

| Role | Working Animation Details |
|------|--------------------------|
| Programmer | Hunched over keyboard, 0/1 bubbles float up |
| Artist | Stylus/pen motion, colorful dots appear |
| Sound Designer | Headphones on, musical notes float up |
| Game Designer | Scratching chin, lightbulb appears |
| Writer | Pen to paper motion, text squiggles |
| Marketer | Phone to ear, megaphone icon |
| Producer | Clipboard, pointing gesture |

### Spritesheet Layout
```
staff_sprites.png

Row 0:  Character 01 — idle(2) | working(4) | walking(4) | celebrate(3) | sad(2) | sleep(2) | sick(2)
Row 1:  Character 02 — same layout
...
Row 19: Character 20 — same layout

Total per row:  19 frames × 16px = 304px wide
Total rows:     20 characters × 16px = 320px tall
Spritesheet:    304 × 320 px
```

### Legendary Staff (Special Sprites)
5 legendary staff with unique, more detailed sprites (still 16×16 but more expressive/distinctive):

| Legendary | Visual Hook |
|-----------|-------------|
| The Veteran | Gray hair, glasses, retro shirt |
| The Prodigy | Young, hoodie, energy aura |
| The Rockstar | Sunglasses, leather jacket |
| The Professor | Lab coat, wild hair |
| The Visionary | Beret, turtleneck (Steve Jobs vibe) |

---

## 3. Office / Studio Tiles

### Tile Dimensions
```
Tile size:       16 × 16 px
Grid:            20 × 16 tiles visible (320 × 256 px studio area)
Perspective:     Top-down with slight isometric hint (like Kairosoft)
```

### Floor Tiles

| Tile | ID | Description |
|------|----|-------------|
| Carpet (basic) | floor_01 | Gray carpet, subtle texture |
| Carpet (nice) | floor_02 | Blue carpet, pattern |
| Hardwood | floor_03 | Wooden floor planks |
| Concrete | floor_04 | Startup garage floor |
| Marble | floor_05 | Premium office (campus) |

### Wall Tiles

| Tile | ID | Description |
|------|----|-------------|
| Basic wall | wall_01 | Off-white, single window |
| Window wall | wall_02 | Large window, sky visible |
| Brick wall | wall_03 | Exposed brick (startup vibe) |
| Glass wall | wall_04 | Modern glass partition |
| Poster wall | wall_05 | Wall with game posters (easter eggs) |

### Furniture — 16×16 or 16×32 (tall items)

| Furniture | Size | Variants | Description |
|-----------|------|----------|-------------|
| Desk + PC | 16×16 | 4 eras | 80s CRT → 90s beige → 2000s LCD → 2010s iMac-style |
| Chair | 16×16 | 2 | Basic, ergonomic |
| Server rack | 16×32 | 1 | For online games research |
| Bookshelf | 16×32 | 2 | Small, large |
| Plant | 16×16 | 3 | Small pot, big plant, bonsai |
| Coffee machine | 16×16 | 1 | +morale visual indicator |
| Whiteboard | 16×32 | 1 | Shows project status doodles |
| Award shelf | 16×32 | 1 | Displays trophies (fills up over time) |
| Couch | 16×16 | 1 | Break area |
| Arcade cabinet | 16×32 | 1 | Unlockable, decorative |
| Water cooler | 16×16 | 1 | Staff gather here during idle |
| Dev kit console | 16×16 | per platform | Changes appearance per era |

### Office Layouts (5 tiers)

| Tier | Name | Grid Size | Capacity | Unlock |
|------|------|-----------|----------|--------|
| 1 | Garage | 10×8 tiles | 4 staff | Start |
| 2 | Small Office | 14×10 | 8 staff | Research |
| 3 | Medium Office | 16×12 | 12 staff | Research |
| 4 | Large Office | 18×14 | 16 staff | Research |
| 5 | Campus | 20×16 | 20 staff | Research |

Each tier needs a **pre-designed tilemap** (JSON) defining furniture placement. Staff pathfind between their desk and common areas.

---

## 4. UI Elements

### UI Frame System
```
All UI panels use a 9-slice pixel border system.
Corner size:     4 × 4 px
Border width:    2 px
Panel bg:        Palette dark (#262b44 or #181425)
Border color:    Palette mid (#5a6988)
Highlight:       1px inner top-left (#8b9bb4)
Shadow:          1px inner bottom-right (#3a4466)
```

### Buttons

| Button State | Size | Description |
|-------------|------|-------------|
| Normal | 64×16 min | Dark fill, 2px border, text centered |
| Hover/Press | 64×16 min | Lighter fill, border highlight |
| Disabled | 64×16 min | Muted colors, text dimmed |
| Icon button | 16×16 | Square, icon only |

### Icons — 8×8 px

Small icons used in stat displays, menus, and HUD.

| Category | Icons Needed |
|----------|-------------|
| Stats | programming, art, sound, design, writing, marketing (6) |
| Resources | money, fans, reputation, hype (4) |
| Actions | hire, fire, train, vacation, crunch, research (6) |
| Genres | 1 icon per genre (20) |
| Themes | 1 icon per theme (21) |
| Phases | planning, alpha, beta, testing, gold (5) |
| Misc | save, load, settings, speed, pause, play, fast-forward (7) |
| Morale | happy, neutral, sad, angry (4) |
| Status | working, idle, training, vacation, sick (5) |
| Awards | trophy, star, ribbon (3) |
| Platforms | console, PC, handheld, mobile, VR (5) |

**Total: ~86 icons at 8×8 px**

### Icons — 16×16 px

Larger icons for main navigation and important UI elements.

| Icon | Count |
|------|-------|
| Bottom nav tabs | 5 (Staff, Project, Marketing, Research, Finance) |
| Project scope | 4 (Mini, Standard, Major, AAA) |
| Genre icons (detail) | 20 |
| Notification types | 6 (event, review, milestone, award, crisis, opportunity) |

**Total: ~35 icons at 16×16 px**

### Font

```
Primary game font:   Custom bitmap font or Press Start 2P
Size:                8px height (uppercase), 7px (lowercase)
Character set:       ASCII printable (32-126) + extended Latin + currency ($€¥£)
CJK fallback:        Noto Sans Mono CJK (for JP/KR/CN locales)

Secondary UI font:   Slightly larger, 10px height
Usage:               Headers, important numbers, titles
```

### Number Display
```
Cash display:        "$" + formatted number (e.g., "$1,234,567")
                     Green if positive change, red if negative
                     Animated counter (rolls up/down)
Review score:        Large "8.5" with /10 smaller
                     Color: red (<4) → orange (<6) → yellow (<8) → green (≥8)
Progress bars:       8px tall, segmented pixel fill
                     Color per phase (green → blue → purple → orange → gold)
```

---

## 5. Game Cartridge / Product Sprites

When a game is completed, a cartridge/disc/download icon is generated.

### Dimensions
```
Cartridge:    16 × 20 px (8-bit / 16-bit era)
CD case:      16 × 18 px (32-bit → HD era)
Digital:      16 × 16 px (mobile / modern era)
```

### Variation System
Each completed game gets a procedurally tinted cartridge:
- **Base color:** derived from genre (RPG = blue, Action = red, Puzzle = green, etc.)
- **Label pattern:** 1 of 4 random tiny patterns (stripe, dot, star, plain)
- **Quality indicator:** gold border if review ≥ 9, silver if ≥ 7, plain otherwise

### Era-Appropriate Formats

| Era | Format | Visual |
|-----|--------|--------|
| 1985-1995 | Cartridge | Chunky rectangle, label on front |
| 1995-2007 | CD/DVD case | Jewel case, disc visible |
| 2007+ | Digital | App icon style, rounded corners |

---

## 6. Effects & Particles

All effects are small sprite animations, not shader-based (keeps it retro).

### Particle Sprites — 4×4 or 8×8 px

| Effect | Size | Frames | Trigger |
|--------|------|--------|---------|
| Sparkle | 4×4 | 3 | Good review, level up |
| Confetti | 4×4 | 4 (×6 colors) | Game completed, award won |
| Coin | 8×8 | 4 | Cash earned |
| Musical note | 8×8 | 2 (×3 colors) | Sound designer working |
| Code bits (0/1) | 4×4 | 2 | Programmer working |
| Color dots | 4×4 | 3 | Artist working |
| Bug (skull) | 8×8 | 2 | Bug found |
| Lightbulb | 8×8 | 3 | Innovation/discovery |
| Heart | 8×8 | 2 | Morale boost |
| Storm cloud | 16×16 | 3 | Bad event, low morale |
| Zzz | 8×8 | 3 | Staff sleeping/resting |
| Exclamation mark | 8×8 | 2 | Event trigger |
| Star | 8×8 | 4 (spin) | Award, milestone |
| Arrow up (green) | 8×8 | 2 | Stat increase |
| Arrow down (red) | 8×8 | 2 | Stat decrease |

**Total: ~15 particle types, ~45 individual frames**

### Overlay Effects

| Effect | Size | Description |
|--------|------|-------------|
| Rain overlay | 320×568 | Parallax rain drops (bad event active) |
| Sunshine | 320×568 | Golden tint + sparkles (good period) |
| Screen flash | Full screen | White flash on milestone (2 frames) |

---

## 7. Screens & Overlays

### Title Screen
```
Size:            320 × 568 px
Elements:
  - Logo:        "GAME DEV STUDIO" in pixel font, ~160×40 px
  - Subtitle:    Smaller text below
  - Animated:    Tiny studio scene (desk, character, CRT monitor)
  - Buttons:     "NEW GAME", "CONTINUE", "SETTINGS"
  - Background:  Dark with subtle pixel grid pattern
```

### "While You Were Away" Screen
```
Size:            320 × 568 px (modal overlay)
Elements:
  - Header:      "While You Were Away..."
  - Stats list:  Weeks passed, $ earned, projects progressed
  - Events:      List of triggered events (scrollable)
  - Button:      "OK" to dismiss
  - Animation:   Cash counter rolling up
```

### Review Screen
```
Size:            280 × 400 px (centered modal)
Elements:
  - Game cartridge:   Center top, large (32×40 px scaled up)
  - Game title:       Below cartridge
  - Magazine scores:  3 rows, each with magazine name + score
  - Average score:    Large, center, color-coded
  - Quote:            Italic text, random review quote
  - Animation:        Score reveals one by one (0.5s delay each)
```

### Award Ceremony Screen
```
Size:            320 × 568 px (full screen takeover)
Elements:
  - Stage:       Bottom third, curtain sides
  - Trophy:      Center, large (32×32), animated sparkle
  - Text:        Award name, game name
  - Confetti:    Particle system, 20-30 confetti pieces
  - Sound:       Fanfare SFX
  - Duration:    3-5 seconds, tap to dismiss
```

---

## 8. Audio Assets

### Music — Chiptune / 8-bit

| Track | BPM | Duration | Era | Mood |
|-------|-----|----------|-----|------|
| Theme 1 — 8-bit Era | 120 | 60-90s loop | 1985-1992 | Cheerful, simple, NES-like |
| Theme 2 — 16-bit Era | 130 | 60-90s loop | 1990-1998 | Richer, SNES-like |
| Theme 3 — 3D Era | 140 | 60-90s loop | 1995-2005 | More complex, PS1-like |
| Theme 4 — Modern Era | 110 | 60-90s loop | 2005-2015 | Chillwave/lo-fi chiptune |
| Title Screen | 100 | 30-60s loop | — | Nostalgic, inviting |
| Award Ceremony | 140 | 15-20s | — | Triumphant fanfare |
| Game Over | 80 | 10s | — | Melancholic, short |

**Format:** OGG Vorbis, 44.1kHz, mono, ~128kbps
**Total music:** ~7-8 tracks, ~8-10 minutes of audio
**Source options:** Commission from chiptune artist ($200-500), generate with tools (FamiTracker, BeepBox, Bosca Ceoil), or use CC0 chiptune libraries

### Sound Effects

| SFX | Duration | Description |
|-----|----------|-------------|
| ui_click | 50ms | Short blip, high pitch |
| ui_back | 80ms | Slightly lower blip |
| ui_confirm | 100ms | Rising two-tone |
| ui_error | 150ms | Buzzer / wrong |
| project_start | 300ms | Ascending jingle |
| project_complete | 500ms | Fanfare, triumphant |
| review_good | 400ms | Cha-ching / sparkle |
| review_bad | 400ms | Wah-wah descending |
| review_reveal | 200ms | Drum roll tick |
| cash_earn | 100ms | Coin sound |
| cash_spend | 100ms | Coin drop |
| level_up | 500ms | RPG level-up jingle |
| staff_hire | 300ms | Welcome chime |
| staff_fire | 300ms | Door close |
| event_trigger | 300ms | Alert chime, mysterious |
| event_good | 400ms | Positive jingle |
| event_bad | 400ms | Ominous tone |
| award_win | 800ms | Epic fanfare (short) |
| research_complete | 400ms | Discovery jingle |
| milestone_reach | 500ms | Achievement unlock |
| notification | 200ms | Subtle ping |
| tick_speed_change | 100ms | Click |
| pause | 100ms | Pause sound |
| prestige | 1000ms | Grand ascending arpeggio |

**Total SFX:** ~24 sounds
**Format:** OGG Vorbis, 44.1kHz, mono
**Source options:** Generate with sfxr/jsfxr (free), bfxr, or commission

---

## 9. Cosmetic Packs (IAP Content)

These are additional assets created for monetization. Not needed at launch, can be added post-launch.

### Office Skin Packs ($0.99 each)

| Pack | Theme | Palette Shift |
|------|-------|---------------|
| Cyberpunk | Neon purple/cyan, dark bg, holographic screens | Remap base palette |
| Vaporwave | Pink/cyan/purple, marble floors, palm plants | Custom palette |
| Cottage | Warm wood, green plants, cozy lighting | Warm palette |
| Space Station | Metal walls, star windows, sci-fi furniture | Cool/gray palette |
| Retro Arcade | Arcade carpets, neon signs, cabinet decorations | Neon palette |

Each pack = re-skinned floor tiles (5) + wall tiles (5) + furniture color variants (~10 items) = ~20 tiles per pack.

### Staff Sprite Packs ($0.99 each)

| Pack | Style | Description |
|------|-------|-------------|
| Chibi | 16×16, bigger heads | Cute, exaggerated proportions |
| Minimalist | 16×16, fewer pixels | Abstract, clean |
| Robot | 16×16, mechanical | Metal bodies, LED eyes |

Each pack = 20 character variants × 19 frames = 380 frames per pack.

### Music Packs ($1.99 each)

| Pack | Genre | Tracks |
|------|-------|--------|
| Synthwave | Synthwave/outrun | 4 era themes re-composed |
| Lo-fi | Lo-fi hip hop | 4 chill background tracks |
| Orchestral | Chiptune + orchestral hybrid | 4 more epic arrangements |

---

## 10. Asset Production Pipeline

### Phase 4 (Start of art production)
```
Week 1:  Finalize palette + pixel grid rules
         Create 5 placeholder staff sprites (1 per role)
         Create basic floor + wall tiles
         Test rendering pipeline in Compose Canvas

Week 2:  Create all 20 staff sprite bases (idle + working only)
         Create all furniture sprites
         Create 8×8 icon set (stats, resources, actions)

Week 3:  Add walking + celebrating + sad animations to staff
         Create game cartridge variants
         Create particle effects
         Create 16×16 navigation icons
```

### Phase 6 (Polish)
```
Week 1:  Create title screen art
         Create review screen layout
         Create award ceremony screen
         Add sleeping + sick staff animations

Week 2:  Create all 20 genre icons (8×8 + 16×16)
         Create all 21 theme icons (8×8)
         Create legendary staff sprites (5)

Week 3:  Commission / create music tracks (4 era themes + title + award)
         Create / generate all SFX (24 sounds)
         Create overlay effects (rain, sunshine)

Week 4:  Final polish pass on all sprites
         Ensure palette consistency
         Optimize spritesheets (texture atlas packing)
         Export final assets
```

### Asset Checklist Summary

| Category | Count | Dimensions | Priority |
|----------|-------|------------|----------|
| Staff sprites (base) | 20 characters × 19 frames | 16×16 | Phase 4 |
| Legendary staff | 5 characters × 19 frames | 16×16 | Phase 6 |
| Floor tiles | 5 | 16×16 | Phase 4 |
| Wall tiles | 5 | 16×16 | Phase 4 |
| Furniture | ~15 items | 16×16 or 16×32 | Phase 4 |
| Office layouts | 5 tilemaps | Varies | Phase 4 |
| Icons (small) | ~86 | 8×8 | Phase 4 |
| Icons (large) | ~35 | 16×16 | Phase 4 |
| Game cartridges | 3 base types | 16×16/20 | Phase 4 |
| Particle effects | ~15 types | 4×4 or 8×8 | Phase 6 |
| Overlay effects | 3 | Full screen | Phase 6 |
| UI frames (9-slice) | 3 variants | 12×12 source | Phase 4 |
| Buttons | 3 states | 64×16 min | Phase 4 |
| Title screen | 1 | 320×568 | Phase 6 |
| Review screen | 1 | 280×400 | Phase 6 |
| Award screen | 1 | 320×568 | Phase 6 |
| Music tracks | 7-8 | OGG, ~60-90s each | Phase 6 |
| Sound effects | ~24 | OGG, <1s each | Phase 6 |

### Total Pixel Art Workload Estimate
- **Staff sprites:** 20×19 + 5×19 = 475 frames at 16×16 → ~40-60 hours
- **Tiles & furniture:** ~30 tiles → ~8-12 hours
- **Icons:** ~120 icons → ~15-20 hours
- **Effects & particles:** ~50 frames → ~5-8 hours
- **Screens & UI:** ~10-15 hours
- **TOTAL PIXEL ART:** ~80-120 hours (2-3 weeks full-time, or spread across Phase 4-6)

### Tools
- **Pixel art:** Aseprite ($20, industry standard for sprites + animations)
- **Tilemaps:** Tiled (free, exports JSON for level layouts)
- **Music:** BeepBox (free, browser) or FamiTracker (free, desktop)
- **SFX:** jsfxr (free, browser) — generates retro SFX procedurally
- **Spritesheet packing:** TexturePacker or free alternatives (ShoeBox)
- **Color palette:** Lospec (palette browser, export for Aseprite)

---

*Asset Specification v1.0 — Game Dev Studio*
*Phases 1-3: use placeholders. Phases 4-6: produce final assets per this spec.*
*All dimensions are in game pixels (1 game pixel = N screen pixels via integer scaling).*
