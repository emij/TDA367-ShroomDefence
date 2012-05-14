package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.utilities.Position;

public enum MovementEnum {
	MOVE_UP {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			Position pos = Position.valueOf(p);
			float speedDelta = moveSpd * delta;
			pos.move(0, -speedDelta);
			return pos;
		} 
	},
	
	MOVE_DOWN {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			Position pos = Position.valueOf(p);
			float speedDelta = moveSpd * delta;
			pos.move(0, speedDelta);
			return pos;
		} 
	},
	
	MOVE_RIGHT {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			Position pos = Position.valueOf(p);
			float speedDelta = moveSpd * delta;
			pos.move(speedDelta, 0);
			return pos;
		} 
	},
	
	MOVE_LEFT {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			Position pos = Position.valueOf(p);
			float speedDelta = moveSpd * delta;
			pos.move(-speedDelta, 0);
			return pos;
		}
	},
	
	NO_MOVEMENT {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			return p;
		} 
	};
	
	abstract Position newPosition(Position p, int delta, float moveSpd);
}
