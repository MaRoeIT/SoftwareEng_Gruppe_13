package adapters;

import no.hiof.g13.ports.in.ServerSignalPort;
import no.hiof.g13.SignalListener;

public class SendSignalUseCase implements ServerSignalPort {
    private final SignalListener signalListener;

    public SendSignalUseCase() {
        this.signalListener = new SignalListener();
    }

    @Override
    public void sendSignal(int signal) {
        signalListener.reciveSignal(signal);
        System.out.println("Signal sent from adapter " + signal);
    }
}
