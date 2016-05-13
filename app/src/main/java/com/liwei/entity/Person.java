package com.liwei.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wei.li on 2015/10/26.
 */
public class Person  implements Parcelable{


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };



    private int id;
    private String name;
    private String address;
    private String number;
    private String email;

    public Person(int id, String name, String address, String number, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person=["+ this.getId()+","+this.getAddress()+","+this.getEmail()+","
                + this.getName()+","+this.getNumber()+"]";
    }
}
