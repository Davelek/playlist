import helpers.PlConsts;
import model.Song;
import view.BottomPanel;
import view.TopPanel;
import view.PlaylistTable;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Boot {
    private JFrame frame;
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private JTable table;
    private PlaylistTable playlistTable;
    private ArrayList<Song> playlist;

    private Song firstSong;
    private Song secondSong;

    public Boot() {
        try {
            playlist = this.initPlaylist();
            firstSong = playlist.get(0);
            secondSong = playlist.get(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();
//        playlistTable = new PlaylistTable(playlist);
        frame = new JFrame();

        frame.setTitle("Nečarův hudební přehrávač");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.initComponents();
        this.initFirstData();
        this.btnEvents();
        totalTime();


        frame.pack();
        frame.setSize(PlConsts.windowsSize);
        frame.setMaximumSize(PlConsts.windowsSize);
        frame.setMinimumSize(PlConsts.windowsSize);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void totalTime(){
        long total = 0;
        for (Song song : playlist) {
            total += song.getSongLengthInSeconds();
        }
        bottomPanel.setLbTotalValue("Celkový čas: " + ConvertTimeFromSecToMMSS(total));
    }

    public String ConvertTimeFromSecToMMSS(long secs){
        Duration dur = Duration.ofSeconds(secs);
        return String.format("%02d:%02d:%02d",
                dur.toHours(), dur.toMinutes(), dur.toMillis());
    }

    private void btnEvents(){
        bottomPanel.getBtnDown().addActionListener(e -> {
            if (!table.getSelectionModel().isSelectionEmpty() && table.getSelectedRow() < playlist.size() - 1){
                Song nextSong = playlist.get(table.getSelectedRow() + 1);
                int selectedIndex = table.getSelectedRow();
                playlist.set(selectedIndex + 1, playlist.get(selectedIndex));
                playlist.set(selectedIndex, nextSong);
                playlistTable.refreshTable();
                table.setRowSelectionInterval(selectedIndex + 1, selectedIndex + 1);

                CurrentAndNextSongChanged();
            }
        });

        bottomPanel.getBtnUp().addActionListener(e -> {
            if (!table.getSelectionModel().isSelectionEmpty() && table.getSelectedRow() > 0){
                Song previousSong = playlist.get(table.getSelectedRow() - 1);
                int selectedIndex = table.getSelectedRow();
                playlist.set(selectedIndex - 1, playlist.get(selectedIndex));
                playlist.set(selectedIndex, previousSong);
                playlistTable.refreshTable();
                table.setRowSelectionInterval(selectedIndex - 1, selectedIndex - 1);

                CurrentAndNextSongChanged();
            }
        });
    }

    private void CurrentAndNextSongChanged() {
        if (firstSong != playlist.get(0)){
            firstSong = playlist.get(0);
            topPanel.setCurrTitleValue(firstSong.getTitle());
            topPanel.setCurrArtistValue(firstSong.getArtist());
            topPanel.setCurrSongLengthValue(firstSong.getSongLengthInMMSS());
        }

        if (secondSong != playlist.get(1)){
            secondSong = playlist.get(1);
            topPanel.setNextTitleValue(secondSong.getTitle());
            topPanel.setNextArtistValue(secondSong.getArtist());
            topPanel.setNextSongLengthValue(secondSong.getSongLengthInMMSS());
        }
    }

    private void initFirstData(){
        // current song
        topPanel.setCurrTitleValue(playlist.get(0).getTitle());
        topPanel.setCurrArtistValue(playlist.get(0).getArtist());
        topPanel.setCurrSongLengthValue(playlist.get(0).getSongLengthInMMSS());

        // next song
        topPanel.setNextTitleValue(playlist.get(1).getTitle());
        topPanel.setNextArtistValue(playlist.get(1).getArtist());
        topPanel.setNextSongLengthValue(playlist.get(1).getSongLengthInMMSS());
    }

    private void initComponents() {
        frame.getContentPane().add(topPanel.getMainPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(bottomPanel.getMainPanel(), BorderLayout.SOUTH);
        try {
            playlistTable = new PlaylistTable(playlist);
            table = new JTable(playlistTable);
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage());
            System.exit(1);
        }
        playlistTable.refreshTable();
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
//        frame.getContentPane().add(playlistTable.getPanel(), BorderLayout.CENTER);
    }

    private ArrayList<Song> initPlaylist() throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\res\\songs.csv";
        File playlistFile = new File(path);
        ArrayList<Song> pl = new ArrayList<Song>();

        if (playlistFile.isFile()){
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String row;
            while ((row = reader.readLine()) != null){
                String[] rawSong = row.split(",");
                Song s = new Song(rawSong[0], rawSong[1], Integer.parseInt(rawSong[2]));
                pl.add(s);
            }
        }

        return pl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Boot();
            }
        });
    }
}
