package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;

public enum MovementEnum {
	MOVE_UP {
		@Override
		void moveCharacter(Position p, int delta) {
			if(p.getY() > 1) {
				p.decrementY();
			};
		} 
	},
	
	MOVE_DOWN {
		@Override
		void moveCharacter(Position p, int delta) {
			if(p.getY() < 639) {
				p.incrementY();
			};
		} 
	},
	
	MOVE_RIGHT {
		@Override
		void moveCharacter(Position p, int delta) {
			if(p.getX() < 799) {
				p.incrementX();
			};
		} 
	},
	
	MOVE_LEFT {
		@Override
		void moveCharacter(Position p, int delta) {
			if(p.getX() > 1) {
				p.decrementX();
			};
		}
	},
	
	NO_MOVEMENT {
		@Override
		void moveCharacter(Position p, int delta) {
			
		} 
	};
	
	abstract void moveCharacter(Position p, int delta);
}
