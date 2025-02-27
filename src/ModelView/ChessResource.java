/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author kamus
 */
public class ChessResource {
    private String title;
    private String type; // Libro o Video
    private String phase; // Apertura, Medio Juego, Final
    private String url;

    public ChessResource(String title, String type, String phase, String url) {
        this.title = title;
        this.type = type;
        this.phase = phase;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getPhase() {
        return phase;
    }

    public String getLink() {
        return url;
    }

    @Override
    public String toString() {
        return title + " (" + type + ") - " + phase + "\n" + url;
    }
}