package adapters;

import no.hiof.g13.ports.in.ServerSignalPort;
import no.hiof.g13.SignalListener;

/**
 * Use case for sending a signal to the core. The int SendSignal takes will be delivered to the core
 * and the core will act according to what signal number is given
 *
 * Example usage:
 * <pre>
 *     SendSignalUseCase sendSignalUseCase = new SendSignalUseCase();
 *
 *     sendSignalUseCase.sendSignal(20)
 * </pre>
 * 20 and 30 makes the core send a MyProduct list to adapters
 */
public class SendSignalUseCase implements ServerSignalPort {
    //CoreClassThat handles signals
    private final SignalListener signalListener;

    public SendSignalUseCase() {
        this.signalListener = new SignalListener();
    }

    /**
     * Takes an int to send a signal to the core
     *
     * @param signal
     */
    @Override
    public void sendSignal(int signal) {
        signalListener.receiveSignal(signal);
        System.out.println("Signal sent from adapter " + signal);
    }
}
