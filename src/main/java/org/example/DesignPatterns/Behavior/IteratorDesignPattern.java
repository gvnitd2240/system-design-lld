package org.example.DesignPatterns.Behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * https://algomaster.io/learn/lld/iterator
 * The Iterator Design Pattern is a behavioral pattern that provides a
 * standard way to access elements of a collection
 * sequentially without exposing its internal structure.
 *
 * At its core, the Iterator pattern is about separating the
 * logic of how you move through a collection from the collection itself.
 * Instead of letting clients directly access internal arrays, lists,
 * or other data structures,
 * the collection provides an iterator object that handles traversal.
 *
 * 1. traverse a collection
 * 2. multiple ways to iterate -> e.g., forward, backward, filtering, or skipping elements)
 * 3. decouple traversal logic from collection structure
 *
 * When faced with this need, developers often write custom for
 * loops or expose the underlying data structures
 * (like ArrayList or LinkedList) directly. For example,
 * a Playlist class might expose its songs array
 * and let the client iterate however it wants.
 *
 * 1. client tightly coupled to the collection’s internal structure
 * 2. it violates encapsulation
 * 3. If the internal storage changes, the client code breaks
 *
 * Clients work with hasNext() and next()
 * completely unaware of whether the underlying structure is an array,
 * a linked list, a tree, or something more exotic.
 *
 * */
public class IteratorDesignPattern {
    /**
     * 1. The Problem: Traversing a Playlist
     * Imagine you are building a music streaming application.
     * Users can create playlists, add songs, and play them
     * in various ways. A playlist might contain hundreds
     * of songs, and the player needs to iterate through them one by one.
     * */

    static class IteratorDesignPatternNaive{
        class Playlist{
            private List<String> songs = new ArrayList<>();


            public void addSong(String song) {
                songs.add(song);
            }

            public List<String> getSongs() {
                return songs;
            }
        }

        class MusicPlayer {
            public void playAll(Playlist playlist) {
                for (String song : playlist.getSongs()) {
                    System.out.println("Playing: " + song);
                }
            }
        }

        /** Why This Becomes a Problem?
         * 1. Breaks Encapsulation
         *      By returning the internal list, you allow clients to do more t
         *      han just read. They can add songs, remove songs, clear the list,
         *      or even replace it entirely. Nothing prevents a client from calling
         *      playlist.getSongs().clear() and wiping out the entire playlist.
         *
         * 2. Tightly Couples Client to Implementation
         * 3. Limited Traversal Options
         * 4. Testing becomes difficult
         * */
    }

    /** What We Really Need?
     * We need a way for clients to traverse the playlist that:
     * Does not expose the internal data structure
     * Provides a consistent interface regardless of how songs are stored
     * Allows the playlist to control how iteration happens
     * Supports different traversal strategies without modifying client code
     * */

    /**
     *  Iterator Pattern:
     *  The Iterator Pattern defines a separate object, the iterator,
     *  that encapsulates the details of traversing a collection.
     *  Instead of exposing its internal structure, the collection
     *  provides an iterator that clients use to access elements sequentially.
     *
     *  The pattern separates two concerns:
     *  1. The collection knows how to store elements
     *  2. The iterator knows how to traverse those elements
     *
     *  This separation means you can change how elements are stored
     *  without affecting how they are traversed, and vice versa.
     *
     *  1. Iterator (interface)
     *  2. ConcreteIterator
     *  3. IterableCollection (interface)
     *  4. ConcreteCollection
     * */

    static class IteratorDesignPatternImpl{
        interface Iterator<T>{
            boolean hasNext();
            T next();
        }

        interface IterableCollection <T>{
            Iterator<T> createIterator();
        }

        static class PlaylistIterator implements Iterator<String>{
            private final Playlist playlist;
            private int index = 0;

            PlaylistIterator(Playlist playlist) {
                this.playlist = playlist;
            }

            @Override
            public boolean hasNext() {
                return index < playlist.getSize();
            }

            @Override
            public String next() {
                return playlist.getSongAt(index++);
            }
        }

        static class Playlist implements IterableCollection<String>{
            private List<String> songs = new ArrayList<>();

            public void addSong(String song) {
                songs.add(song);
            }

            public String getSongAt(int index) {
                return songs.get(index);
            }

            public int getSize() {
                return songs.size();
            }

            @Override
            public Iterator<String> createIterator() {
                return new PlaylistIterator(this);
            }
        }
        static class MusicPlayer {
            public static void play() {
                Playlist playlist = new Playlist();
                playlist.addSong("Shape of You");
                playlist.addSong("Bohemian Rhapsody");
                playlist.addSong("Blinding Lights");

                Iterator<String> iterator = playlist.createIterator();
                System.out.println("Now Playing:");

                while(iterator.hasNext()){
                    System.out.println(" 🎵 " + iterator.next());
                }
            }
        }

        /**
         * Client -> Playlist -> PlaylistIterator -> Iterator
         * Playlist -> Iterable Collection
         *
         * 1. No break of encapsulation.
         * 2. Implementation independence
         * 3. Single Responsibility Principle
         * 4. Multiple simultaneous traversals
         * 5. Foundation for extensions
         * */
    }

    public static void main(String[] args) {
        IteratorDesignPatternImpl.MusicPlayer.play();
    }
}
