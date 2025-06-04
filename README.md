# SpaceInvaders

A minimalist Java 21 + **OpenJFX 21** remake of the 1978 *Space Invaders* arcade shooter — no game engines, no external graphics, just clean JavaFX `Canvas` calls.

Features at a glance 🛸

* Five-column alien armada (small, medium, large) plus the passing **mystery ship**
* Player cannon that glides left/right and fires single-shot lasers
* Three destructible **barriers** that deform as they absorb hits
* Alien speed ramps up as their numbers fall, faithful to the original’s rising tension
* Scoreboard with in-session **best-score** tracker
* Splash screen, game-over panel and one-click restart
* 100% vector-drawn, nothing to download; runs in < 650 LOC

---

## Table of Contents

1. [Installation](#installation)
2. [Running the Game](#running-the-game)
3. [Controls](#controls)
4. [Project Layout](#project-layout)
5. [Contributing](#contributing)
6. [License](#license)

---

## Installation

> *Tested on Windows 10/11, macOS Sonoma 14, Ubuntu 22.04 with JDK 21 + OpenJFX 21.*

### 1  Clone the repo

```bash
git clone https://github.com/your-username/SpaceInvaders.git
cd SpaceInvaders
```

### 2  Install prerequisites

| Tool           | Windows (winget/choco)                     | macOS (brew)              | Ubuntu / Debian                                |
| -------------- | ------------------------------------------ | ------------------------- | ---------------------------------------------- |
| **JDK 21**     | `winget install OpenJDK.Temurin.21`        | `brew install openjdk@21` | `sudo apt install temurin-21-jdk`              |
| **OpenJFX 21** | Download SDK ➜ unzip to `C:\javafx-sdk-21` | `brew install openjfx`    | `sudo apt install openjfx` or grab the tarball |

Set `PATH_TO_FX` (folder containing `javafx.controls.jar`, normally the `lib/` dir of the SDK):

```bash
# macOS / Linux (bash/zsh)
export PATH_TO_FX=/opt/homebrew/opt/openjdk/libexec/openjfx/lib

# Windows (PowerShell)
$Env:PATH_TO_FX="C:\javafx-sdk-21\lib"
```

*(Skip this if `openjfx` was installed via package manager that symlinks the jars.)*

### 3  Compile

```bash
javac --module-path "$PATH_TO_FX" \
      --add-modules javafx.controls,javafx.graphics \
      -d out $(ls *.java)
```

### 4  Run

```bash
java --module-path "$PATH_TO_FX" \
     --add-modules javafx.controls,javafx.graphics \
     -cp out Window
```

> **Tip:** A pre-built but non-executable JAR (`CW4 - Space Invaders.jar`) is included; you can rebuild an executable fat-jar with:
>
> ```bash
> jar --create --file SpaceInvaders.jar -C out .
> echo "Main-Class: Window" > Manifest.txt
> jar --update --file SpaceInvaders.jar --manifest=Manifest.txt
> ```

---

## Running the Game

1. A retro menu appears on launch: click **START** 
2. Survive the alien onslaught and rack up points
3. After *GAME OVER*, hit **BACK TO MENU** to try again

---

## Controls

| Action              | Key                      |
| ------------------- | ------------------------ |
| Move left           | ← (Left Arrow)           |
| Move right          | → (Right Arrow)          |
| Fire laser          | Space                    |
| Restart (from menu) | **START** button / Enter |

*(All key handling lives in `GameDisplay.java`, so feel free to remap.)*

---

## Project Layout

```
.
├── Alien.java            # Base class for all invaders
├── AlienSwarm.java       # Manages formation movement & shooting
├── Barrier.java          # Shield segments with health
├── Game.java             # Core game-state + collision logic
├── GameDisplay.java      # JavaFX Canvas renderer + main loop
├── Laser.java            # Projectile objects
├── Player.java           # Cannon behaviour
├── Window.java           # JavaFX Application/UI chrome
└── *.java (SmallAlien, MediumAlien, LargeAlien, SpecialAlien, Sprite)
```

A coursework report (`SpaceInvadersReport.pdf`) is also bundled for design rationale and UML diagrams.

---

## Contributing

1. **Fork** the repo
2. `git checkout -b feature/your-idea`
3. `git commit -am "✨ Add your idea"`
4. `git push origin feature/your-idea`
5. Open a **Pull Request** — all help welcomed!

Please ensure code passes `javac --release 21` and follows the existing Javadoc style.

---

## License

MIT—see [`LICENSE`](LICENSE).

---

> *Shoot straight, protect those barriers, and may your high-score climb to the stars!*
