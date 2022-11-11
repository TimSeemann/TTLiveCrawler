package org.example;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Stats {
    private Set<Spieler> spielerList;

    public Stats(Set<Spieler> spielerList) {
        this.spielerList = spielerList;
    }

    public List<Spieler> sortedByPoints(){
        List<Spieler> out = new java.util.ArrayList<>(this.spielerList.stream().toList());
        out.sort(Comparator.comparingInt(s -> - s.getLpzWerte().get(0).getPoints()));
        return out;
    }

    public List<Pair<Spieler,Integer>> punkteUnterschiedSeit(LocalDate date){
        List<Pair<Spieler,Integer>> punkteListe = new ArrayList<>();
        for(Spieler spieler : this.spielerList){
            List<Historieneintrag> punkte = spieler.getLpzWerte();
            for(int i=0;i<punkte.size();i++){
                if(punkte.get(i).getDate().isBefore(date)){
                    punkteListe.add(new ImmutablePair<>(spieler,
                            punkte.get(0).getPoints()-punkte.get(i).getPoints()
                            ));
                    break;
                }
            }
        }
        punkteListe.sort(Comparator.comparingInt(p -> - p.getRight()));
        return punkteListe;
    }

}