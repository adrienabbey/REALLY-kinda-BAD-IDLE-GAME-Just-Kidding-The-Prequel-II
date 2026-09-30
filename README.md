# REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II

> Or RKBIGJKTP2 for short

## Preface
This GitHub repository contains the deliverables, Java source, and development documentation produced by a four-person team for CEG 4110, Introduction to Software Engineering, at Wright State University in Spring 2024.

## Synopsis
This fantasy idle game is loosely inspired by Melvor Idle and Kingdom of Loathing. It includes Swing-based navigation, combat, character progression, save/load behavior, a world map, inventory, crafting/resource concepts, and multiple locations. The team used an Agile-like process to practice requirements, planning, design, implementation, testing, documentation, meetings, and delivery.

## Contributors and Roles 
(see Software Development plan for project role definitions)
- Adrien Abbey: PO, PM, Dev, QA
- Brandon Walker: PM, Dev, QA
- Muhammed Abushamma: Documenter, PM, Dev, QA
- Luke Davidson: PM, Dev, QA

## Build and Run

Requirements: JDK 17 or newer and a desktop environment with audio support. Run commands from the repository root because the application loads assets with repository-relative paths.

```bash
mkdir -p build/classes
javac -d build/classes src/*.java
java -cp build/classes Driver
```

The application launches full-screen. Some media files have incomplete or uncertain redistribution terms; review [`docs/Credits and Licensing.md`](docs/Credits%20and%20Licensing.md) before redistributing a build or asset bundle.

## Brandon Walker's Contribution

Brandon served as project manager, developer, and QA contributor. Git history shows sustained work across application navigation and orchestration (`Driver`, `StartScreen`, and `World`), character creation/progression, combat and dungeon behavior, settings/credits, save/load integration, requirements/design documentation, and team coordination. This is contribution evidence within a team project, not a claim of sole authorship.

## Important Files and Folders

- `src/`: Java/Swing application source.
- `assets/`: images, music, sound effects, and video assets with mixed third-party licensing.
- `docs/`: proposal, planning, requirements, design, meeting, journal, and credit records.
- [Project Overview Proposal](docs/pop.md)
- [Software Development Plan](docs/sdp.md)
- [Requirements Specification](docs/rsd.md)
- [Design Specification](docs/dsd.md)
- [Presentation](https://docs.google.com/presentation/d/1IhkBFzaC2Gv5R91MlzrjNmFNw9Ya-nX9/edit?usp=sharing&ouid=109920732234565522830&rtpof=true&sd=true)
- [Video demo](https://www.youtube.com/watch?v=rp4dO3-pJ6Y)

## Status and Ownership

The course project is complete but not packaged as a supported release. The repository is owned by Adrien Abbey and reflects contributions from the full team. The team has approved this portfolio-oriented README cleanup. No blanket reuse license is implied by public visibility, and third-party assets must still be reviewed under their respective terms before redistribution.
