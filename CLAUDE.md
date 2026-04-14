# Game Dev Studio -- Claude Code Instructions

## Key Documents
- `docs/GDD_GameDevStudio_v2.1.md` -- Full game design document (all systems, formulas, data)
- `docs/ASSET_SPEC_GameDevStudio.md` -- Asset specs, Canvas placeholder code, palette, audio
- GDD Section 14 contains the full Claude Code Phase 1 brief with file list and MVP data

## Current State
- **Scaffolding complete** -- all 53 stub files created with correct signatures and TODO comments
- **No game logic implemented yet** -- every method body is a TODO placeholder
- **Build not verified** -- needs `./gradlew assembleDebug` on a machine with Android SDK 35
- **Next step: Phase 1 implementation** -- make the MVP playable

## Architecture Rules
- **Kotlin only** -- no Java
- **Compose only** -- no XML layouts
- **MVVM + Clean Architecture** -- ViewModels expose StateFlow, UI observes
- **Room DB** for persistence -- not SharedPreferences
- **Coroutines** for game loop -- no Thread
- **Hilt** for dependency injection
- **KSP** for Room and Hilt annotation processing

## Code Organization
- All game formulas go in `domain/util/Formulas.kt`
- All balance constants go in `domain/data/Balance.kt`
- All user-facing strings go in `res/values/strings.xml` (i18n-ready)
- Package: `com.gamedevstudio`

## Visual Style (Phases 1-3)
- All sprites drawn in **Compose Canvas** -- no PNG assets yet
- Pixel art: `isAntiAlias = false`, `FilterQuality.None`, integer coordinates
- Palette: Endesga 32 (see `Palette` object in Asset Spec)
- Staff sprites: 6x10 game pixels at PIXEL_SCALE = 3
- See `docs/ASSET_SPEC_GameDevStudio.md` section 0 for all Canvas drawing code
- Section 0.1: Staff sprite pixel-by-pixel draw code
- Section 0.2: Furniture (desk, monitor, chair, plant, bookshelf, vending machine)
- Section 0.3: Particle effects (confetti, coins, speech bubbles)
- Section 0.4: Full StudioView composable assembly
- Section 0.5: Animation loop (300ms/frame, 4 frames)

## Game Loop
- 1 tick = 1 in-game week
- Base speed: 2 real seconds = 1 tick
- Speeds: X1 (2000ms), X2 (1000ms), X4 (500ms), X8 (250ms, premium)
- Start year: 1985, typical playthrough: ~30 years to 2015
- Main loop per tick: advance time, update projects, calc revenue, pay salaries,
  update staff, check events, update fanbase, check milestones, trigger reviews, auto-save

## MVP Data (Phase 1)
- 3 genres: ACTION, PUZZLE, ADVENTURE (with stat weights in GDD section 14)
- 3 themes: MODERN, FANTASY, SPACE (with synergy matrix in GDD section 14)
- Scope: MINI only (8-12 weeks, 1 dev)
- Starting cash: $50,000
- Game price: $29.99
- Sales: 5,000 units/week per review point, -8%/week decay
- 5 dev phases: Planning (10%), Alpha (40%), Beta (30%), Testing (15%), Gold (5%)
- 3 MVP magazines: GamePower Monthly, Pixel Perfect, Console Warrior

## Phase 1 Deliverable
Playable APK where you can:
1. Name your studio
2. Start developing a game (genre + theme + title)
3. Watch progress week by week through 5 phases
4. Receive reviews (3 magazines)
5. Watch sales accumulate with decay
6. Build up cash
7. Start a new project
8. Auto-save via Room DB

## Constraints
- Min SDK 26, Target SDK 35
- Portrait orientation only
- Material 3 base fully overridden for pixel theme
- One-handed UI optimized for thumb reach
