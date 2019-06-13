package br.com.beblue.domain.disc;

public enum Genre {
    CLASSIC("Classic"),
    MPB("MPB"),
    POP("Pop"),
    ROCK("Rock");

    private String name;

    Genre(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
