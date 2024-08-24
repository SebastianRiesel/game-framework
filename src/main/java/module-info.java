module game_framework {
    requires transitive javafx.controls;
	requires transitive javafx.graphics;


    exports de.basti.game_framework.graphics;
    exports de.basti.game_framework.graphics.animation;
    exports de.basti.game_framework.graphics.gui; 
    exports de.basti.game_framework.collision;
    exports de.basti.game_framework.collision.system;
    exports de.basti.game_framework.input;
    exports de.basti.game_framework.core;
    exports de.basti.game_framework.math;
    
    
}
