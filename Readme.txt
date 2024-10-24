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

