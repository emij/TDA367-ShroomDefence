package se.chalmers.tda367.std.core.events;

/**
 * This event is fired when a new wave has been initialized.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class WaveStartedEvent {
	private final int releasedWaveCount;
	
	public WaveStartedEvent(int releasedWaveCount) {
		this.releasedWaveCount = releasedWaveCount;
	}

	/**
	 * @return the released wave count.
	 */
	public int getReleasedWaveCount() {
		return releasedWaveCount;
	}
}
