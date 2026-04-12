# GAME DEV STUDIO — Asset Specification
### Complete production guide for all visual and audio assets
**Tool:** Aseprite (pixel art) / Compose Canvas (placeholders)  
**Palette:** Endesga 32 (32 colors) — or custom 32-color palette  
**Rendering:** `image-rendering: pixelated` / Compose `FilterQuality.None`  
**Export:** PNG (sprites), OGG (audio), JSON (sprite atlases)

---

## 0. Placeholder Strategy (Phases 1-3)

During early development, **no final assets are needed**. Use these placeholders:

### Compose Canvas Placeholders
```kotlin
// Staff placeholder: colored circle + role letter
Canvas(modifier = Modifier.size(24.dp)) {
    drawCircle(color = roleColor, radius = size.minDimension / 2)
    // Draw role initial: "P", "A", "S", "D", "W", "M"
}

// Office placeholder: gray rectangle grid
// Furniture: smaller colored rectangles
// Game cartridge: small colored square with border
```

### Emoji Fallbacks (ultra-fast prototyping)
| Entity | Emoji | When to use |
|--------|-------|-------------|
| Programmer | 🧑‍💻 | Phase 1 MVP |
| Artist | 👩‍🎨 | Phase 1 MVP |
| Sound Designer | 🎵 | Phase 1 MVP |
| Game Designer | 🎮 | Phase 1 MVP |
| Writer | ✍️ | Phase 1 MVP |
| Marketer | 📢 | Phase 1 MVP |
| Office desk | 🖥️ | Phase 1 MVP |
| Money | 💰 | Phase 1 MVP |
| Game cartridge | 📦 | Phase 1 MVP |
| Trophy | 🏆 | Phase 1 MVP |

### When to Switch to Real Assets
- **Phase 4+** (Meta & Events): Start with staff sprites and office tiles
- **Phase 6** (Polish): Complete all remaining sprites, icons, animations
- **Before launch:** Everything must be final pixel art

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
