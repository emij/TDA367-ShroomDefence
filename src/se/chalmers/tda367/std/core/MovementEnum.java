package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;

public enum MovementEnum {
	MOVE_UP {
		@Override
		Position newPosition(Position p, int delta) {
			Position pos = Position.valueOf(p);
			pos.decrementY();
			pos.decrementY();
			return pos;
		} 
	},
	
	MOVE_DOWN {
		@Override
		Position newPosition(Position p, int delta) {
			Position pos = Position.valueOf(p);
			pos.incrementY();
			pos.incrementY();
			return pos;
		} 
	},
	
	MOVE_RIGHT {
		@Override
		Position newPosition(Position p, int delta) {
			Position pos = Position.valueOf(p);
			pos.incrementX();
			pos.incrementX();
			return pos;
		} 
	},
	
	MOVE_LEFT {
		@Override
		Position newPosition(Position p, int delta) {
			Position pos = Position.valueOf(p);
			pos.decrementX();
			pos.decrementX();
			return pos;
		}
	},
	
	NO_MOVEMENT {
		@Override
		Position newPosition(Position p, int delta) {
			return p;
		} 
	};
	
	abstract Position newPosition(Position p, int delta);
}
