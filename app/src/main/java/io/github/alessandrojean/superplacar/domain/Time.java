package io.github.alessandrojean.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desktop on 14/12/2017.
 */

public class Time implements Parcelable {
    private String nome;
    private String imagemUrl;
    private int gols;
    private List<Gol> golsLista;

    public Time() {
        golsLista = new ArrayList<>();
    }

    protected Time(Parcel in) {
        this.nome = in.readString();
        this.imagemUrl = in.readString();
        this.gols = in.readInt();
        this.golsLista = in.createTypedArrayList(Gol.CREATOR);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public List<Gol> getGolsLista() {
        return golsLista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nome);
        parcel.writeString(this.imagemUrl);
        parcel.writeInt(this.gols);
        parcel.writeTypedList(this.golsLista);
    }

    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel parcel) {
            return new Time(parcel);
        }

        @Override
        public Time[] newArray(int i) {
            return new Time[i];
        }
    };
}
