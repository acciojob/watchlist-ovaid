package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieMap = new HashMap<>();
    HashMap<String, Director> directorMap = new HashMap<>();
    HashMap<String, List<String>> pairMap = new HashMap<>();

    public String addMovie(Movie movie){
        String ans = movie.getName();
        movieMap.put(ans,movie);
        return "Movie is successfully added";
    }

    public String addDirector(Director director){
        String ans = director.getName();
        directorMap.put(ans,director);
        return "Director is successfully added";
    }

    public String addMovieDirectorPair(String movieName,String directorName) {
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)) {
            List<String>list = new ArrayList<>();
            if(pairMap.containsKey(directorName)) {
                if(pairMap.get(directorName).contains(movieName)) {
                    return "Pair already exist";
                }
                list = pairMap.get(directorName);
                list.add(movieName);
                pairMap.put(directorName,list);
            }
            else {
                list.add(movieName);
                pairMap.put(directorName,list);
            }
            return "Pair added successfully";
        }
        return "database does not contains pair";
    }

    public Movie getMovieByName(String movieName){
        if(movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        return null ;
    }

    public Director getDirectorByName(String directorName){
        if(directorMap.containsKey(directorName)){
            return directorMap.get(directorName);
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName){
        if(pairMap.containsKey(directorName)){
            return pairMap.get(directorName);
        }
        return null;
    }

    public List<String> findAllMovies(){
        List<String> ans=new ArrayList<>();
        for(String m : movieMap.keySet()){
            ans.add(m);
        }
        return ans;
    }

    public String deleteDirectorByName(String directorName){
        List<String> list =new ArrayList<>();
        if(pairMap.containsKey(directorName)){
            list=pairMap.get(directorName);
        }
        for(String movie:list) {
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        pairMap.remove(directorName);
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
        return "Director and its movies removed successfully";
    }

    public String deleteAllDirectors(){

        for(String s:pairMap.keySet()){
            List<String> ans = new ArrayList<>();
            ans = pairMap.get(s);

            for(String movie:ans) {
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            pairMap.remove(s);
        }

        for(String s:directorMap.keySet()){
            directorMap.remove(s);
        }
        return "All directors and all of their movies removed successfully";
    }
}