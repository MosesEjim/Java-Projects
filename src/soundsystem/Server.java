/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundsystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author CS LAB 4
 */
public class Server extends Thread {

    static MulticastSocket socket;
    static DatagramPacket packet;
    static TargetDataLine mic;
    String groupIP = "228.5.6.7";

    public AudioFormat getAudioFormat() {

        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channel = 2;
        boolean signed = true;
        boolean bidEdian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channel, signed, bidEdian);

    }

    public Server() {

        try {

            socket = new MulticastSocket();
            mic = (TargetDataLine) AudioSystem.getTargetDataLine(getAudioFormat());
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, getAudioFormat());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
                mic.open();
                mic.start();
                byte data[] = new byte[1024];
            while (true) {
               
                mic.read(data, 0, data.length);
                packet = new DatagramPacket(data, data.length, InetAddress.getByName(groupIP), 8888);
                socket.send(packet);
                System.out.println("sending data ...");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        socket.close();
        mic.close();
        mic.drain();
    }

}
