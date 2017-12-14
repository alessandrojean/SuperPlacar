package io.github.alessandrojean.superplacar.extras;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.alessandrojean.superplacar.MainActivity;
import io.github.alessandrojean.superplacar.domain.Gol;
import io.github.alessandrojean.superplacar.domain.Jogo;
import io.github.alessandrojean.superplacar.domain.Time;

public class SuperPlacarRequest extends AsyncTask<Void, Void, List<Jogo>> {
    private WeakReference<MainActivity> activity;

    public SuperPlacarRequest(MainActivity mainActivity) {
        this.activity = new WeakReference<>(mainActivity);
    }

    @Override
    protected List<Jogo> doInBackground(Void... voids) {
        Document html;
        List<Jogo> jogos = new ArrayList<>();

        try {
            html = Jsoup.connect("http://www.superplacar.com.br/").get();

            Elements time = html.select("div.time-status span.time");
            Elements status = html.select("div.time-status span.status");
            Elements times1 = html.select("div.team1");
            Elements times2 = html.select("div.team2");

            for (int i = 0; i < time.size(); i++) {
                Time time1 = getTime(times1.get(i), true);
                Time time2 = getTime(times2.get(i), false);

                Jogo jogo = new Jogo();
                jogo.setInicio(time.get(i).text());
                jogo.setStatus(status.get(i).text());
                jogo.setTime1(time1);
                jogo.setTime2(time2);

                jogos.add(jogo);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return jogos;
    }

    private Time getTime(Element timeTag, boolean ehCasa) {
        int indice = ehCasa ? 1 : 2;
        Time time = new Time();
        time.setNome(timeTag.select("span.team" + indice + "-name").text());
        time.setImagemUrl(timeTag.select("img").attr("src"));

        String golsString = timeTag.select("span.team" + indice + "-score").text();
        int gols = golsString.isEmpty() ? 0 : Integer.parseInt(golsString);
        time.setGols(gols);

        time.getGolsLista().addAll(getGolsLista(timeTag));

        return time;
    }

    private List<Gol> getGolsLista(Element timeTag) {
        Elements golsLista = timeTag.select("ul.goal-players li");
        List<Gol> gols = new ArrayList<>();

        for (Element g : golsLista) {
            Gol gol = new Gol();
            gol.setNome(g.select(".name").text());
            gol.setTime(g.select(".time").text());
            gols.add(gol);
        }

        return gols;
    }

    @Override
    protected void onPostExecute(List<Jogo> jogos) {
        super.onPostExecute(jogos);

        if (activity.get() != null) {
            activity.get().updateLista(jogos);
        }
    }
}
