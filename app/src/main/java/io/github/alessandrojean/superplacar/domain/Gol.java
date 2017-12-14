package io.github.alessandrojean.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desktop on 14/12/2017.
 */

public class Gol implements Parcelable {
    private String nome;
    private String time;

    public Gol() {

    }

    protected Gol(Parcel in) {
        this.nome = in.readString();
        this.time = in.readString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nome);
        parcel.writeString(this.time);
    }

    public static final Parcelable.Creator<Gol> CREATOR = new Parcelable.Creator<Gol>() {
        @Override
        public Gol createFromParcel(Parcel parcel) {
            return new Gol(parcel);
        }

        @Override
        public Gol[] newArray(int i) {
            return new Gol[i];
        }
    };
}
