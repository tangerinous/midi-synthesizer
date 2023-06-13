import javax.sound.midi.*;
import javax.swing.text.TabExpander;
import java.util.Scanner;

// 1. Знакомство с Java Midi
// 2. Считать пользовательский ввод (ноты)
// 3. Как эту ноту воспроизвести
// 4. Повторяем 2 и 3


public class Main {
    private static final int C = 60; //do
    private static final int D = 62; //re
    private static final int E = 64; //mi
    private static final int F = 65; //fa
    private static final int G = 67; //sol
    private static final int A = 69; //la
    private static final int B = 70; //si

    public static void main(String[] args) throws Exception {

        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        Receiver receiver = synthesizer.getReceiver();

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        // A A B C D E G

        while (!text.equals("exit")) {
            String[] music = text.split(" ");
            for (int i = 0; i < music.length; i++) {

                int noteId = convertNote(music[i].toUpperCase().trim());
                playNote(receiver, noteId);

            }

            text = scanner.nextLine();
        }
    }

    private static void playNote(Receiver receiver, int noteId) throws InvalidMidiDataException, InterruptedException {
        ShortMessage message = new ShortMessage(ShortMessage.NOTE_ON, noteId, 100);
        receiver.send(message, -1);
        Thread.sleep(1000L);
        ShortMessage offMsg = new ShortMessage(ShortMessage.NOTE_OFF, noteId, 100);
        receiver.send(offMsg, -1);
    }

    public static int convertNote(String note) {
        switch (note) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            case "E":
                return E;
            case "F":
                return F;
            case "G":
                return G;
            default:
                return A;
        }
    }
}