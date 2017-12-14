package io.github.alessandrojean.superplacar.extras;

import java.util.ArrayList;
import java.util.List;

import io.github.alessandrojean.superplacar.domain.Gol;
import io.github.alessandrojean.superplacar.domain.Jogo;
import io.github.alessandrojean.superplacar.domain.Time;

/**
 * Created by Desktop on 14/12/2017.
 */

public class Mock {
    public static List<Gol> gerarGols(int n) {
        String[] times = {"16'1T", "35'1T", "01'2T", "21'2T"};
        String[] nomes = {"Fernando", "Michael", "Léo Castro", "João Paulo"};
        List<Gol> gols = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int randomPos = (int) (Math.random() * 4);
            Gol g = new Gol();

            g.setTime(times[randomPos]);
            g.setNome(nomes[randomPos]);
            gols.add(g);
        }

        return gols;
    }

    public static Time gerarTime(int i){
        String[] nomes = {"Rio Claro", "São Caetano", "S. J. Campos", "Nacional-SP"};
        String[] imagens = {
                "http://www.superplacar.com.br/images/escudos/f1eab3ac03d333dc76278b2f7989bace-68.png",
                "http://www.superplacar.com.br/images/escudos/173fb38f10e9a24e7cc665e513575bf2-68.png",
                "http://www.superplacar.com.br/images/escudos/a4cd88615deb2decbe7515b74849bee9-68.png",
                "http://www.superplacar.com.br/images/escudos/42ecf680e39db12f2ba513263694d1bc-68.PNG"
        };
        int[] gols = {0, 2, 1, 0};

        Time time = new Time();
        time.setNome(nomes[i]);
        time.setImagemUrl(imagens[i]);
        time.setGols(gols[i]);
        time.getGolsLista().addAll(gerarGols(gols[i]));

        return time;
    }

    public static Jogo gerarJogo(int i){
        String[] status = {"Em andamento", "Em breve", "Encerrado"};
        String[] inicios = {"16:55", "19:00", "20:00"};

        Jogo jogo = new Jogo();
        jogo.setTime1(gerarTime(i));
        jogo.setTime2(gerarTime(i + 1));
        jogo.setStatus(status[i]);
        jogo.setInicio(inicios[i]);

        return jogo;
    }

    public static ArrayList<Jogo> gerarJogos(){
        ArrayList<Jogo> jogos = new ArrayList<>();
        jogos.add(gerarJogo(0));
        jogos.add(gerarJogo(2));

        return jogos;
    }
}
