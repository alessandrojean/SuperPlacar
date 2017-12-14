package io.github.alessandrojean.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desktop on 14/12/2017.
 */

public class Jogo implements Parcelable {
    public static final String JOGOS_KEY = "jogos_key";

    private Time time1;
    private Time time2;
    private String status;
    private String inicio;

    public Jogo() {

    }

    protected Jogo(Parcel in) {
        this.time1 = in.readParcelable(Time.class.getClassLoader());
        this.time2 = in.readParcelable(Time.class.getClassLoader());
        this.status = in.readString();
        this.inicio = in.readString();
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.time1, i);
        parcel.writeParcelable(this.time2, i);
        parcel.writeString(this.status);
        parcel.writeString(this.inicio);
    }

    public static final Parcelable.Creator<Jogo> CREATOR = new Parcelable.Creator<Jogo>() {
        @Override
        public Jogo createFromParcel(Parcel parcel) {
            return new Jogo(parcel);
        }

        @Override
        public Jogo[] newArray(int i) {
            return new Jogo[i];
        }
    };
}
