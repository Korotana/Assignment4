# CMPT 381 Assignment 3: 2D Graphics, MVC, Multiple Views

## Overview
This project is a JavaFX visual editor for building state-machine diagrams. It demonstrates concepts such as 2D graphics, immediate-mode interaction, the Model-View-Controller (MVC) architecture, multiple synchronized views, and view panning. The editor allows users to interactively create state nodes and transition links, edit them, and pan through the workspace.

### Features
- **Part 1:** Basic system with state node creation and linking
- **Part 2:** Full transition links with event, context, and side effect representation
- **Part 3:** Editing and deletion of state nodes and transition links
- **Part 4:** View synchronization, viewport panning, and window resizing

## How to Run the Application
1. Ensure that you have JavaFX installed.
2. Open the project in IntelliJ IDEA or any other IDE with JavaFX support.
3. Build and run the project. The main class is `EditorApp`.

### Key Interactions:
- **Pointer Tool:** Create and move state nodes.
- **Link Tool:** Create transition links between nodes.
- **Pan Tool:** Drag to move the viewport.
- **Property Panel:** Edit state node or transition properties.
- **Delete Key:** Deletes the selected node or transition.

## Project Structure
- **Application Class:**
  - `EditorApp`: Main application class.
- **Model Classes:**
  - `SMModel`: Manages state nodes and transition links.
  - `SMStateNode`, `SMTransitionLink`: Represent state nodes and transition links.
- **View Classes:**
  - `MainUI`, `ToolPalette`, `DiagramView`: Define different views.
- **Controller Class:**
  - `AppController`: Handles user input and controls interaction.

## Functionality Status
- **Part 1:** Fully implemented (state nodes, links, and MVC structure).
- **Part 2:** Transition links with additional details (event, context, side effects) are fully working.
- **Part 3:** Node and transition editing, along with deletion, is implemented.
- **Part 4:** Panning, resizing, and view synchronization is working as expected.

## Known Issues
- None identified at the moment. If any interaction fails, please ensure the JavaFX version is correctly installed.

## Dependencies
- JavaFX 17+
- No external libraries are required other than JavaFX.

## How to Compile and Run
1. Install JavaFX (version 17 or above).
2. Import the project into your IDE (IntelliJ IDEA recommended).
3. Compile and run `EditorApp.java`.

### Note
Ensure that you have correctly configured your IDE to run JavaFX projects, including setting the VM options:



# Target Practice Graphical Editor

## Overview
This project is a 2D graphical editor built using JavaFX that allows users to create, resize, and manipulate circular targets. It supports multiple selection, undo/redo, and cut/copy/paste functionalities. Additionally, it includes a target training mode where users can test their clicking accuracy on targets, with performance statistics displayed in a final report.
Features Implemented

    ## Target Creation, Selection, and Manipulation 
        Create Targets: Shift-clicking on the background creates a new target.
        Move Targets: Clicking and dragging moves targets.
        Resize Targets: Shift-clicking and dragging allows resizing targets.
        Single and Multiple Selection: Users can select targets individually or using rubber-band-lasso for multiple selections. Control-click is used to add/remove selections.
        Delete Targets: Pressing Delete removes selected targets.

    ## Undo/Redo
        Create, delete, move, and resize actions can be undone (Control-Z) or redone (Control-R).
        Supports undo/redo functionality with multiple targets.

    ## Cut/Copy/Paste 
        Cut (Control-X): Removes selected items and stores them in the clipboard.
        Copy (Control-C): Copies selected items to the clipboard.
        Paste (Control-V): Pastes items from the clipboard into the model, with support for multiple pastes.

    ## Targeting Trainer
        Test Mode: Switch to a test view using Control-T, where users can click through targets.
        Performance Report: A chart of performance data is shown, plotting Movement Time (MT) vs. Index of Difficulty (ID).
        Mode Switching: Control-E switches back to the editor view.


Just run the program as a normal java program.

