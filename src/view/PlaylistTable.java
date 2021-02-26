package view;

import model.Song;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistTable extends AbstractTableModel {
    private ArrayList<Song> playlist;

    public PlaylistTable(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    @Override
    public int getRowCount() {
        return playlist.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int index){
         switch (index) {
             case 0 : return  "#";
            case 1 : return  "Title";
            case 2 : return  "Artist";
            case 3 : return  "Length";
            default : return  null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
         switch (columnIndex) {
            case 0 : return  String.class;
            case 1 : return  String.class;
            case 2 : return  String.class;
            case 3 : return  String.class;
            default : return  null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Song s = playlist.get(rowIndex);
         switch (columnIndex) {
            case 0 : return  rowIndex + 1;
            case 1 : return  s.getTitle();
            case 2 : return  s.getArtist();
            case 3 : return  s.getSongLengthInMMSS();
            default : return  null;
        }
    }

    public void refreshTable(){
        fireTableDataChanged();
    }
}
