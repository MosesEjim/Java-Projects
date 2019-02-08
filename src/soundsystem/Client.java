/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundsystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author CS LAB 4
 */
public class Client extends Thread {

    static MulticastSocket socket;
    static DatagramPacket packet;
    static TargetDataLine mic;
    String groupIP = "228.5.6.7";

    SourceDataLine speaker;

    Client() {
        try {
            DataLine.Info infoOut = new DataLine.Info(SourceDataLine.class, getAudioFormat());
            speaker = (SourceDataLine) AudioSystem.getLine(infoOut);
            socket = new MulticastSocket(8888);
            socket.joinGroup(InetAddress.getByName(groupIP));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public AudioFormat getAudioFormat() {

        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bidEdian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channel, signed, bidEdian);

    }

    @Override
    public void run() {

        try {
            
            speaker.open();
            speaker.start();
            byte data[] = new byte[1024];
            packet = new DatagramPacket(data,data.length);
            
            while (true) {
                
                socket.receive(packet);
                speaker.write(packet.getData(), 0, data.length);
                
            }

        } catch (Exception e) {
            
            System.out.println(e);
        }
        speaker.drain();
        speaker.close();
        socket.close();
    }

}
