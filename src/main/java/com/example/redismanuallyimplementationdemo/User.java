package com.example.redismanuallyimplementationdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {

     int id;

     String name;

     int age;

     String country;

}
