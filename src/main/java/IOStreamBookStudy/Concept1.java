package IOStreamBookStudy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author Anthony Z.
 * @Date 22/7/2022
 * @Description: Naive version
 *
 * Every TCP segment contains at least 40 bytes of overhead for routing
 * and error correction.
 *
 * Most TCP/IP implementations accumulate bytes in memory
 * and send them to their eventual destination only when
 * 1. a certain number have accumulated; or
 * 2. a certain amount of time has passed.
 *
 * However, if you have more than one byte to go, it's not a
 * bad idea to end them all at once.
 *
 * Using write(byte[] data) or write(byte[] data, int offset, int length)
 * is normally much faster than writing all components of the data array
 * one at a time
 */
public class Concept1 {
    // The entire methods is declared to throw IOException.
    // That's important because the character-generator will terminate
    // only when the client closes the connection.
    // Java will see this as an IOException.
    public static void generateCharacters(OutputStream out) throws IOException {

        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacter = 94;
        int numberOfCharactersPerLine = 72;
        byte[] line = new byte[numberOfCharactersPerLine + 2];
        // +2 for carriage return and line feed.

        int start = firstPrintableCharacter;
        while (true){
            for(int i = start; i<start + numberOfCharactersPerLine; i++){
                line[i - start] = (byte) ((i - firstPrintableCharacter)%numberOfPrintableCharacter+firstPrintableCharacter);

//                out.write(((i - firstPrintableCharacter)%numberOfPrintableCharacter)+firstPrintableCharacter);
//
//                // After each 72-character chunk is written,
//                // a carriage return and a linefeed are written
//                // onto the output stream.
//                out.write('\r'); // carriage return
//                out.write('\n'); // linefeed
//
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';
            out.write(line);
            // the bytes are packed into a byte array before being
            // written onto the network.
            start = ((start + 1) - firstPrintableCharacter)
                    % numberOfPrintableCharacter + firstPrintableCharacter;

        }
    }
}
