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

		@Override
		Position newJumpPosition(Position p) {
			Position pos = Position.valueOf(p);
			int tileScale = Properties.INSTANCE.getTileScale() + 2;
			pos.move(0, -tileScale);
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

		@Override
		Position newJumpPosition(Position p) {
			Position pos = Position.valueOf(p);
			int tileScale = Properties.INSTANCE.getTileScale() + 2;
			pos.move(0, +tileScale);
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

		@Override
		Position newJumpPosition(Position p) {
			Position pos = Position.valueOf(p);
			int tileScale = Properties.INSTANCE.getTileScale() + 2;
			pos.move(tileScale, 0);
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

		@Override
		Position newJumpPosition(Position p) {
			Position pos = Position.valueOf(p);
			int tileScale = Properties.INSTANCE.getTileScale() + 2;
			pos.move(-tileScale, 0);
			return pos;
		}
	},
	
	NO_MOVEMENT {
		@Override
		Position newPosition(Position p, int delta, float moveSpd) {
			return p;
		}

		@Override
		Position newJumpPosition(Position p) {
			return p;
		} 
	};
	
	abstract Position newPosition(Position p, int delta, float moveSpd);
	abstract Position newJumpPosition(Position p);
}
