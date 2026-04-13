# GAME DEV STUDIO — Game Design Document v2.1
### Idle tycoon game dev simulator · Kairosoft-inspired · Android · Worldwide EN
**Stack:** Kotlin / Jetpack Compose / Room DB / Hilt  
**Art Direction:** Retro Pixel Art (32-color palette)  
**Platform:** Android (Google Play — Worldwide)  
**Monetization:** Free-to-Play (Rewarded Ads + IAP)  
**Target:** $3K/mo MRR  

---

## 01. Vision & Concept

### Elevator Pitch
Starting in 1985, you run a tiny game studio. Hire developers, pick genres and platforms, ship games, manage marketing campaigns, land console deals, and become a legendary studio. Retro pixel art aesthetic with a deeply satisfying idle/incremental loop you can leave running in your pocket.

### Gameplay Pillars
1. **Team Management** — Hire, train, specialize, motivate
2. **Game Development** — Genre/theme combos, resource allocation, quality vs deadline
3. **Business & Marketing** — Ad budgets, hype, release timing, console deals
4. **Research & Tech** — Tech tree unlocking genres, platforms, features
5. **Prestige & Legacy** — Awards, Hall of Fame, New Game+

### References
- **Game Dev Story** (Kairosoft) — Structure, flow, hiring
- **Game Dev Tycoon** (Greenheart Games) — Genre/theme combos, reviews
- **Idle Game Dev** — Idle mechanics, prestige
- **Mad Games Tycoon** — Business depth, console deals

---

## 02. Android Architecture

### Tech Stack
| Component | Technology |
|-----------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose (Canvas API for pixel art) |
| Architecture | MVVM + Clean Architecture |
| State | StateFlow + Room DB |
| DI | Hilt |
| Background | WorkManager (offline progress) |
| Audio | Oboe / MediaPlayer (chiptune) |
| Analytics | Firebase Analytics + Crashlytics |
| Ads | AdMob (rewarded + interstitial) |
| IAP | Google Play Billing v7 |
| Min SDK | API 26 (Android 8.0) — covers ~95% of devices |
| Target SDK | API 35 (Android 15) |

### Project Structure
```
app/src/main/java/com/gamedevstudio/
├── di/                    // Hilt modules
├── data/
│   ├── db/                // Room entities + DAO
│   ├── repository/        // Repositories
│   └── prefs/             // DataStore prefs
├── domain/
│   ├── model/             // Game models
│   ├── engine/            // GameLoop, TimeManager
│   ├── systems/           // Staff, Project, Finance...
│   └── usecase/           // Use cases
├── ui/
│   ├── theme/             // Pixel art Compose theme
│   ├── components/        // Reusable pixel widgets
│   ├── screens/
│   │   ├── title/         // Title screen
│   │   ├── game/          // Main game screen
│   │   ├── staff/         // Staff management
│   │   ├── project/       // Project dev
│   │   ├── research/      // Tech tree
│   │   └── shop/          // IAP store
│   └── navigation/        // Nav graph
├── worker/                // WorkManager jobs
├── notification/          // Push notifications
└── billing/               // Google Play Billing
```

### Android-Specific Features

**WorkManager (Offline Progress):** When the player closes the app, ticks accumulate and are calculated on return (catch-up). Push notifications for key events (game shipped, review, random event).

**Room DB (Persistence):** Persistent local save via Room. JSON export/import for Google Drive backup. Auto-save every minute and on critical state changes.

**One-Handed UI:** Optimized for one-handed play. Bottom navigation, swipe between panels, primary actions within thumb reach. Portrait only.

### Background Handling
```kotlin
// When app returns to foreground:
fun onResume() {
    val lastTick = dataStore.getLastTickTimestamp()
    val now = System.currentTimeMillis()
    val elapsedMs = now - lastTick
    val ticksToProcess = (elapsedMs / TICK_DURATION_MS)
        .coerceAtMost(MAX_OFFLINE_TICKS) // Cap at 720 ticks (≈1 in-game month)
    
    // Fast-forward missed ticks
    gameEngine.fastForward(ticksToProcess)
    
    // Show "While you were away..." summary
    showOfflineSummary(ticksToProcess, results)
}
```

**"While You Were Away" Screen:** On return, a summary screen shows weeks elapsed, money earned, project progress, events triggered. Tap "OK" to continue. This is a key satisfaction moment in the idle loop.

---

## 03. Monetization Strategy

### Philosophy
The game is **100% playable for free** with no progression walls. Monetization is built on comfort, customization, and player respect. No pay-to-win, no artificial timers, no forced ads. A F2P player gets the exact same gameplay as a paying player — just less convenience and cosmetics.

### Rewarded Ads (AdMob) — ~40-60% of revenue
The player *chooses* to watch an ad for a bonus. Never forced.

| Trigger | Reward | Cooldown |
|---------|--------|----------|
| Game shipped | +15% game quality | 1 per game |
| After review | +1 review point (cap 10) | 1 per game |
| Hiring | Refresh candidate pool | 1/hour |
| Cash boost | Double this week's sales | 1/30min |
| Returning offline | Double offline earnings | 1 per session |
| Speed training | Cut training time by 50% | 1 per training |
| Bonus event | Unlock a hidden choice | 1 per event |

**eCPM Estimate (Global):** Rewarded eCPM US/UK/AU: ~$10-20. Tier 2 (BR/MX/IN): ~$3-8. With 3-5 views/session, 2 sessions/day, 1K DAU = ~$40-100/day. Target: 10K DAU = $400-1000/day.

### Premium Pass (IAP) — ~25-35% of revenue
One-time purchase. Removes ads and unlocks cosmetic content.

- **$4.99 — Premium Lite:** Remove interstitial ads + x8 speed
- **$9.99 — Premium Full:** All Lite + 10 exclusive themes + 3 office skins + "Gold Dev" badge
- **$14.99 — Collector:** All Full + sandbox mode (unlimited cash, everything unlocked) + 5 legendary exclusive staff traits + early access to future updates

**Target Conversion:** Average idle game IAP conversion: 2-5%. At 10K DAU → 200-500 purchases at ~$8 avg = ~$1,600-4,000 one-time then ongoing with new players.

### Cosmetics (IAP) — ~10-15% of revenue
Visual skins only, zero gameplay impact.

- **$0.99** — Office skin packs (Cyberpunk, Vaporwave, Cottage...)
- **$0.99** — Staff sprite packs (Classic Pixel, Chibi, Minimalist...)
- **$1.99** — Music packs (Synthwave, Lo-fi, Orchestral...)
- **$0.49** — Individual UI color themes

### Seasonal Content — ~5-10% of revenue
- **FREE** — Seasonal event (Christmas, Halloween, E3...) with temporary genre/theme
- **$2.99** — Season pass: exclusive cosmetics + temporary legendary employee
- **FREE** — Weekly leaderboard (best game of the week across all players)

### Revenue Projections
| Metric | Target |
|--------|--------|
| DAU at M+6 | 10K |
| ARPDAU | $0.10 |
| Revenue/day | ~$1K |
| MRR (conservative) | ~$3K |

**Growth Model:** M1-M3: Soft launch (Belgium/Netherlands/Philippines), 500-2K DAU, rapid iteration on retention (D1 > 40%, D7 > 15%). M4-M6: Worldwide Play Store launch, aggressive ASO, target 5-10K DAU. M7+: Seasonal content, ARPDAU optimization, UA ad scaling if LTV > CPI × 1.5.

### Anti-Patterns — What We Will NEVER Do
- ✕ No artificial wait timers ("come back in 4h")
- ✕ No non-skippable interstitial ads (except light F2P without Premium)
- ✕ No lootboxes / gacha (no monetized RNG)
- ✕ No pay-to-win (IAPs are cosmetic or convenience only)
- ✕ No obscure premium currency (gems, crystals, etc.)
- ✕ No progression gated behind payment

---

## 03b. Localization & International Launch

### English-First, Global Reach
The game launches **in English worldwide**. The idle/tycoon genre has a massive global English-speaking audience. English-first maximizes reach, eCPM, and conversion rates. Localization follows based on retention data by region.

### Launch Languages

| Phase | Languages | Why |
|-------|-----------|-----|
| Launch | 🇬🇧 English | Global reach, highest eCPM, biggest tycoon audience |
| M+2 | 🇫🇷 French | Home market, easy to do ourselves |
| M+3 | 🇯🇵 Japanese · 🇰🇷 Korean | Huge Kairosoft-style fanbase in JP/KR |
| M+4 | 🇩🇪 German · 🇪🇸 Spanish · 🇧🇷 Portuguese (BR) | Top Tier 1-2 markets for Android |
| M+6 | 🇨🇳 Simplified Chinese · 🇹🇼 Traditional Chinese | Massive mobile market, strong idle genre |

### Technical i18n Architecture
```kotlin
// All in-game strings go through a centralized
// string resource system from day 1

// res/values/strings.xml (EN - default)
// res/values-fr/strings.xml (FR)
// res/values-ja/strings.xml (JP)

// Dynamic content (review quotes, event text,
// staff names) uses JSON locale files:
assets/locale/
├── en.json    // English (default)
├── fr.json    // French
├── ja.json    // Japanese
├── ko.json    // Korean
└── ...

// Staff name generator per locale:
fun generateStaffName(locale: Locale): String
// EN: "Alex Chen", "Sarah Miller"
// JP: "田中太郎", "佐藤花子"
// FR: "Pierre Dupont", "Marie Laurent"
```

### Localization-Aware Design Rules
- **Flexible UI:** All text containers auto-size. German is ~30% longer than English. Japanese needs different line-height. Test with longest strings.
- **Currency:** In-game currency is always "$" (fictional). IAP prices auto-adapt via Google Play per-region pricing.
- **Pixel Font:** Must support Latin, Japanese (kana + kanji subset), Korean (hangul). Fallback to Noto Sans Mono for CJK.

### ASO by Region

| Market | Play Store Title | Key ASO Keywords | eCPM Tier |
|--------|-----------------|-------------------|-----------|
| 🇺🇸 US/UK | Game Dev Studio: Idle Tycoon | game dev tycoon, idle simulator, kairosoft | $12-20 |
| 🇯🇵 Japan | ゲーム開発スタジオ | ゲーム開発, カイロソフト風, 放置ゲーム | $10-18 |
| 🇰🇷 Korea | 게임 개발 스튜디오 | 게임개발, 방치형, 타이쿤 | $6-12 |
| 🇧🇷 Brazil | Game Dev Studio: Simulador | simulador, tycoon, desenvolvimento de jogos | $3-6 |
| 🇩🇪 Germany | Game Dev Studio: Idle Tycoon | spieleentwicklung, tycoon, simulation | $8-15 |

---

## 04. Game Loop & Time

### Time System
```kotlin
data class TimeState(
    val week: Int,           // 1-52
    val month: Int,          // 1-12
    val year: Int,           // Starts at 1985
    val tickSpeed: Speed,    // x1, x2, x4, x8
    val isPaused: Boolean,
    val totalWeeks: Long
)

enum class Speed(val ms: Long) {
    X1(2000),   // 2s = 1 week
    X2(1000),   // 1s = 1 week
    X4(500),    // 0.5s = 1 week
    X8(250)     // Premium only
}
```

- **1 tick = 1 in-game week**
- **Base tick:** 2 real seconds = 1 week
- **Pause:** manual or automatic (events, end of project, review)
- **Typical playthrough:** ~30 years (1985 → 2015), ~1560 weeks

### Main Loop (per tick)
1. Advance time (week++)
2. Update active projects (dev progression)
3. Calculate passive revenue (ongoing sales)
4. Pay salaries (monthly, week 4 of each month)
5. Update staff (XP, morale, fatigue)
6. Check random events
7. Update fanbase
8. Check milestones / unlocks
9. Trigger reviews if a game just shipped
10. Auto-save (every 4 weeks)

---

## 05. Staff System

### Employee Structure
```kotlin
data class Staff(
    val id: String,
    val name: String,
    val avatar: SpriteId,
    val role: StaffRole,
    val level: Int,             // 1-99
    val xp: Long,
    val salary: Int,            // Weekly cost
    val stats: StaffStats,
    val traits: List<Trait>,    // Max 3
    val morale: Int,            // 0-100
    val fatigue: Int,           // 0-100
    val specialization: Genre?
)

data class StaffStats(
    val programming: Int,  // 1-999
    val art: Int,
    val sound: Int,
    val design: Int,
    val writing: Int,
    val marketing: Int
)

enum class StaffRole {
    PROGRAMMER, ARTIST, SOUND_DESIGNER,
    GAME_DESIGNER, WRITER, MARKETER,
    PRODUCER  // Unlocked via research
}
```

### Personality Traits (12 traits)

| Trait | Positive Effect | Negative Effect |
|-------|----------------|-----------------|
| Workaholic | -50% fatigue, +10% productivity | — |
| Creative | +20% design/art | — |
| Perfectionist | +15% quality | +20% dev time |
| Speedrunner | +25% speed | -10% quality |
| Mentor | +XP for nearby colleagues | — |
| Diva | +30% if morale > 80 | -40% if morale < 50 |
| Team Player | +5% per colleague on project | — |
| Night Owl | +15% productivity | +10% fatigue |
| Innovator | +chance to discover new combos | — |
| Veteran | +bonus to previously dev'd genres | — |
| Budget Guru | -15% dev cost | — |
| Hype Machine | +25% marketing efficiency | — |

### Recruitment & Candidate Pool
The candidate pool refreshes each month. Quality depends on studio reputation.

| Reputation | Candidate Stats | Traits |
|------------|----------------|--------|
| < 20 | 10-50 | 0-1 common trait |
| 20-50 | 30-150 | 0-2 traits |
| 50-80 | 80-400 | 1-2 traits, rare possible |
| > 80 | 200-999 | 2-3 traits, legendary possible |

Capacity: 4 (garage) → 8 → 12 → 16 → 20 (campus). Hiring cost: 2× weekly salary.

### Morale & Fatigue

**Morale (0-100):** Productivity multiplier 0.5× (morale=0) to 1.5× (morale=100). Goes up: project completion bonus, vacation, raise, award won. Goes down: crunch, bad review, colleague fired, bugs.

**Fatigue (0-100):** Normal: +3/week. Crunch: +10/week. Above 80: risk of illness (absent 1-2 weeks). Above 90: risk of quitting. Vacation: 2 weeks → fatigue = 0.

### Training

| Type | Duration | Cost | Effect |
|------|----------|------|--------|
| Programming Bootcamp | 4 weeks | $2K-5K | +30-80 programming |
| Art Workshop | 4 weeks | $2K-5K | +30-80 art |
| Sound Masterclass | 3 weeks | $1K-3K | +30-80 sound |
| Design Seminar | 3 weeks | $1K-3K | +30-80 design |
| Writing Retreat | 4 weeks | $2K-4K | +30-80 writing |
| Marketing Course | 2 weeks | $1K-2K | +30-80 marketing |
| Genre Specialization | 6 weeks | $5K-10K | Learn genre, +20% permanent bonus |
| Leadership Training | 8 weeks | $8K-10K | Unlocks "Producer" role |
| Conference Trip | 2 weeks | $3K-5K | +random XP, +morale, chance of event |

Employee is **unavailable** during training.

---

## 06. Game Development

### Development Phases
| Phase | % of Time | Focus |
|-------|-----------|-------|
| Planning | 10% | Design docs |
| Alpha | 40% | Core dev |
| Beta | 30% | Content + polish |
| Testing | 15% | QA + bugfix |
| Gold | 5% | Finalization |

### 20 Genres
Action, Adventure, RPG, Simulation, Strategy, Puzzle, Platformer, Shooter, Racing, Fighting, Sports, Horror, Visual Novel, Roguelike, Rhythm, Sandbox, MMO, Battle Royale, Idle, Metroidvania

### 21 Themes
Medieval, Sci-Fi, Modern, Post-Apo, Cyberpunk, Steampunk, Fantasy, Space, Pirates, Ninja, Samurai, Mythology, Detective, School, Cooking, Animals, Robots, Zombies, Vampires, Lovecraft, Dating

### Genre × Theme Compatibility Matrix

| Score | Effect | Example |
|-------|--------|---------|
| S | +30% quality | RPG + Fantasy, Horror + Lovecraft |
| A | +15% quality | Shooter + Sci-Fi, Racing + Modern |
| B | +0% (neutral) | Puzzle + Space, Adventure + Pirates |
| C | -10% quality | Sports + Vampires |
| D | -20% quality... but chance of "Cult Classic" if review > 7 | Cooking + Lovecraft (??) |

### Scope & Duration

| Scope | Duration | Team | Typical Budget |
|-------|----------|------|---------------|
| Mini | 8-12 weeks | 1-2 devs | $5-20K |
| Standard | 16-26 weeks | 3-5 devs | $20-100K |
| Major | 30-52 weeks | 5-10 devs | $100-500K |
| AAA | 52-104 weeks | 10-20 devs | $500K-5M |

### Quality Formula
```kotlin
fun calculateQuality(project: GameProject, team: List<Staff>): Float {
    val weights = GENRE_WEIGHTS[project.genre]
    val raw = team.sumStats(weights)
    
    val synergy  = MATRIX[project.genre][project.theme]  // 0.8-1.3
    val morale   = team.avgMorale() / 100f               // 0.0-1.0
    val crunch   = if(project.crunch) 0.85f else 1f      // -15%
    val bugs     = max(0.5f, 1f - project.bugs * 0.02f)
    val sequel   = getSequelBonus(project)               // +10-20%
    
    return (raw / maxScore * 10f
        * synergy * morale * crunch * bugs * sequel)
        .coerceIn(1f, 10f)
}
```

### Bug System
- Bugs accumulate during development (proportional to complexity, inversely to programming stats)
- **Testing phase:** each week, testers find and fix X bugs
- Remaining bugs at launch → review penalty
- **Hotfix post-launch:** option to patch (1-2 weeks, slight score improvement)
- **Day-one patch:** if > 20 bugs, press mentions bugs, -1 review point

---

## 07. Platforms & Consoles

### Console Timeline (fictional names)

| Era | Year | Consoles | Dev Kit | Rep Required |
|-----|------|----------|---------|--------------|
| 8-bit | 1985 | Intec 8 · MicroPC | $5K / free | 0 |
| 16-bit | 1990 | Intec 16 · Sonix Genesis | $12-15K | 10-15 |
| 32/64-bit | 1995 | Sonix Station · Intec 64 | $40-50K | 25-30 |
| 128-bit | 2000 | Sonix Station 2 · Minisoft Box | $80-100K | 35-40 |
| HD | 2005 | Minisoft 360 · Sonix Station 3 · Intec Motion | $50-250K | 20-55 |
| Mobile | 2007 | SmartPhone | $100 | 0 |
| VR | 2015 | VirtuVision | $500K | 70 |

**Handhelds:** Intec Pocket (1989, $3K), Sonix Portable (2004, $30K).

### Console Deals
- **Exclusivity:** Manufacturer offers a deal (marketing bonus + royalty advance, single-platform sales)
- **Launch title:** Shipping within 4 weeks of a console launch = huge visibility boost
- **E4 Expo:** Week 24 each year, massive hype if you present your game there

---

## 08. Marketing & Hype

### Campaign Types (11)

| Campaign | Cost | Hype | Unlocked | Notes |
|----------|------|------|----------|-------|
| 📰 Magazine ad | $500-5K | +5-15 | 1985 | Cheap, medium reach |
| 📺 TV commercial | $10-50K | +20-40 | 1990 | Expensive, huge reach |
| 🌐 Internet ad | $1-10K | +10-25 | ~2000 | Good cost/reach ratio |
| 📱 Social media | $500-5K | +10-30 | ~2008 | Cheap, viral potential |
| 🎙️ Influencer | $2-20K | +5-50 | ~2010 | RNG-based, huge upside |
| 🎪 Convention (E4) | $10-50K | +15-40 | 1990 | Annual, massive hype |
| 📝 Press preview | Free | ±5-20 | 1985 | Risky if low quality |
| 🎬 Trailer | $2-10K | +10-25 | 1995 | Proportional to quality |
| 🕹️ Demo | Free | ±5-15 | 1990 | Public mini-review |
| 👕 Merch | $5K setup | Passive | 50K fans | Passive income |
| 🏆 Esports | $20-100K | +20-50 | 2005 | Long term, specific genres |

### Hype Mechanic
Hype (0-100) decays by **3 points/week** without marketing. At launch: hype < 20 = weak sales even with a good game. hype > 80 = boosted sales but risk of disappointment if quality < 7. Timing is everything.

---

## 09. Reviews & Sales

### 8 Magazines / Websites

| Year | Name | Bias | Harshness |
|------|------|------|-----------|
| 1985 | GamePower Monthly | mainstream | moderate |
| 1988 | Pixel Perfect | indie | lenient |
| 1990 | Console Warrior | console | harsh |
| 2000 | GameVortex.com | mainstream | moderate |
| 2003 | HardcoreGamer.net | hardcore | very harsh |
| 2005 | IndieRadar | indie | very lenient |
| 2010 | PixelPundit (YT) | mainstream | moderate |
| 2013 | RageQuit (Twitch) | hardcore | harsh |

### Sales Model
```
// Sales at week N after launch:
sales = baseSales
    × exp(-0.08 × N)      // 8%/week decay
    × platformShare        // Market share
    × hype / 50            // Hype boost
    × wordOfMouth          // 1.3 if >8, 0.7 if <5

// Spikes:
× 3.0 launch week
× 1.5 Christmas
× 2.0 sale

// Long tail (9+ games):
max(sales, baseSales × 0.05)  // "Classic" floor
```

---

## 10. Random Events

### Industry
- 🎮 Console launch
- 📉 Industry crash
- 🎪 E4 Expo (annual)
- 🏆 AAA competitor release

### Staff
- 🔥 Burnout
- ⭐ Star applicant
- ⚔️ Internal conflict
- 🎓 Sudden inspiration

### Financial
- 🤝 Investor offer
- 📋 Tax audit
- 🎰 Acquisition opportunity

### Opportunity
- 🎬 Movie license
- 🏃 Speedrun community
- 📺 TV/Anime adaptation
- 🌍 International market

### Crisis
- 🔓 Data leak
- 🔥 Servers on fire
- 🐛 Critical post-launch bug
- 📢 Public controversy

### Meta
- 🎄 Seasonal event
- 🏅 Award nomination
- 📰 Press interview
- 🎉 Studio anniversary

---

## 11. Prestige & New Game+

At year 2015 (or voluntary retirement), the player can "prestige": everything resets to 1985 with permanent bonuses.

| Bonus | Per Prestige |
|-------|-------------|
| Starting cash | +50% |
| Staff XP multiplier | +10% |
| Research cost | -5% |
| Starting reputation | +5 |
| Unlocked genres | Kept permanently |

**Hall of Fame:** The best games from each run are immortalized. Ultimate endgame goal: get a 10/10 game in **every genre**. Persists across prestiges and reinstalls (cloud save).

---

## 12. UI/UX & Art Direction

### Visual Style
- **Resolution:** 320×568 logical, upscaled (pixelated rendering)
- **Palette:** 32 colors (Endesga 32 or custom)
- **Sprites:** 16×16 / 24×24, idle animation 2 frames
- **Office:** Simplified top-down isometric view
- **UI:** Windows 95 / Mac Classic pixel art windows
- **Font:** Bitmap pixel (Press Start 2P or custom)

### Mobile Layout (Portrait)
```
┌─────────────────────┐
│ 💰 $125K │ Jun 95 │▶│
├─────────────────────┤
│                     │
│   STUDIO VIEW       │
│   (animated         │
│    characters        │
│    working)          │
│                     │
│  🧑‍💻 👩‍🎨 🎵 🧑‍💻    │
│                     │
├─────────────────────┤
│ ┌─────────────────┐ │
│ │  ACTIVE PANEL   │ │
│ │  (swipe L/R)    │ │
│ │  Staff / Project│ │
│ │  Finance / etc  │ │
│ └─────────────────┘ │
├─────────────────────┤
│ 👥  🎮  📢  🔬  💰  │
│ Staff Proj Mktg Res $│
└─────────────────────┘
```

### Animations & Sound

**Animations:** Dev typing → 0/1 bubbles, Art → colorful stars, Sound → floating notes, Bug → red skull, Game done → confetti, Bad review → rain cloud, Award → golden trophy.

**Music:** 4 chiptune themes evolving with eras (8-bit, 16-bit, 3D era, modern). Loopable.

**SFX:** UI click (blip), New project (rising jingle), Good review (cha-ching), Bad review (wah-wah), Level up (RPG jingle), Award (epic fanfare).

---

## 13. Development Roadmap

| Phase | Duration | Focus |
|-------|----------|-------|
| 1 — Core Loop MVP | 4-5 weeks | Tick-based game loop, 1 dev, genre/theme, progression, review, sales, minimal UI, Room DB |
| 2 — Staff & Studio | 3-4 weeks | Hiring, stats, XP, morale, fatigue, training, office upgrade, animated sprites |
| 3 — Depth | 4-5 weeks | Full genre×theme matrix (20×21), sub-genres, bugs, QA, 11 marketing types, consoles, multi-magazine reviews |
| 4 — Meta & Events | 3-4 weeks | 20+ random events, annual awards, fanbase, research tree (25+ nodes), console deals, sequels |
| 5 — Monetization | 2-3 weeks | AdMob rewarded (7 placements), Google Play Billing, Firebase Analytics, WorkManager offline, notifications |
| 6 — Polish | 3-4 weeks | Prestige/NG+, Hall of Fame, sound design, animations, tutorial, balancing, i18n framework + FR locale |
| 7 — Launch | 2-3 weeks | Soft launch (BE/NL/PH), ASO, Crashlytics, worldwide launch, UA marketing |

**Total estimate:** 22-28 weeks. Target launch: M6-M7.

---

## 14. Claude Code Brief — Phase 1

```
# BRIEF CLAUDE CODE — Game Dev Studio — Phase 1 (Core Loop MVP Android)

## CONTEXT
Idle/incremental game dev studio tycoon, inspired by
Game Dev Story (Kairosoft). Native Android app.
Stack: Kotlin, Jetpack Compose, MVVM + Clean Architecture,
Room DB, Hilt, StateFlow.
Art direction: retro pixel art (32-color palette,
pixelated rendering via Compose Canvas).
Language: English (worldwide launch). All in-game strings
must go through res/values/strings.xml from day 1.

## GOAL PHASE 1
Playable MVP: the player owns a studio with a single dev
(themselves), starts game development (genre + theme),
the game progresses over ticks (weeks), receives a review,
and generates sales.

## PROJECT STRUCTURE
app/src/main/java/com/gamedevstudio/

di/
  AppModule.kt          // Hilt module (DB, repos)

data/
  db/
    GameDatabase.kt     // Room database
    entities/           // Staff, Project, Finance entities
    dao/                // DAO interfaces
  repository/
    GameRepository.kt   // Single source of truth

domain/
  model/
    TimeState.kt        // Week, month, year (start 1985)
    Staff.kt            // Name, role, stats, level, XP
    GameProject.kt      // Title, genre, theme, scope, phase, progress, quality
    Genre.kt            // Enum: ACTION, PUZZLE, ADVENTURE
    Theme.kt            // Enum: MODERN, FANTASY, SPACE
    ReviewResult.kt     // Score 1-10, magazine quotes
    FinanceState.kt     // Cash, revenue, expenses
  engine/
    GameLoop.kt         // Coroutine-based, 2s = 1 week
    TimeManager.kt      // Week/month/year conversion
  systems/
    ProjectSystem.kt    // Project creation, per-tick progression, 5 phases, quality
    ReviewSystem.kt     // Score based on quality + RNG ±0.5, 3 magazines
    FinanceSystem.kt    // $50K start, weekly sales, decay formula
  usecase/
    StartGameUseCase.kt
    CreateProjectUseCase.kt
    TickUseCase.kt

ui/
  theme/
    PixelTheme.kt       // Compose pixel art theme (BG: #0a0a12, Panel: #14151f)
    PixelColors.kt
    PixelTypography.kt
  components/
    PixelButton.kt
    PixelPanel.kt
    ProgressBar.kt
  screens/
    title/TitleScreen.kt, TitleViewModel.kt
    game/GameScreen.kt, GameViewModel.kt, TopBar.kt, StudioView.kt, ProjectPanel.kt, BottomNav.kt
  navigation/
    NavGraph.kt         // Title → Game

## MVP DATA
3 genres: ACTION, PUZZLE, ADVENTURE
  - ACTION weights: prog=0.3, art=0.2, design=0.3, writing=0.1, sound=0.1
  - PUZZLE weights: prog=0.25, art=0.15, design=0.4, writing=0.1, sound=0.1
  - ADVENTURE weights: prog=0.15, art=0.2, design=0.2, writing=0.35, sound=0.1

3 themes: MODERN, FANTASY, SPACE
Synergy matrix:
  ACTION+MODERN=1.15, ACTION+FANTASY=1.0, ACTION+SPACE=1.15
  PUZZLE+MODERN=1.0, PUZZLE+FANTASY=0.9, PUZZLE+SPACE=1.15
  ADVENTURE+MODERN=1.0, ADVENTURE+FANTASY=1.3, ADVENTURE+SPACE=1.15

MVP scope: MINI only (8-12 weeks, 1 dev)
Starting cash: $50,000
Base sales per review point: 5,000 units/week
Sales decay: -8%/week
Game price: $29.99

## CONSTRAINTS
- Kotlin, no Java
- Compose only, no XML layouts
- All formulas in domain/util/Formulas.kt
- Constants in domain/data/Balance.kt
- Room DB for persistence (not SharedPrefs)
- Coroutines for game loop (no Thread)
- Min SDK 26, Target SDK 35
- Material 3 base but fully overridden for pixel theme
- ALL user-facing strings in res/values/strings.xml (i18n-ready)

## DELIVERABLE
Playable APK where you can:
1. Name your studio
2. Start developing a game (genre + theme + title)
3. Watch progress week by week
4. Receive reviews (3 magazines)
5. Watch sales accumulate
6. Build up cash
7. Start a new project
8. Auto-save via Room DB
```

---

*Document v2.1 — Android / Kotlin / Jetpack Compose — Worldwide EN Launch*  
*Each phase can be developed independently with Claude Code.*
