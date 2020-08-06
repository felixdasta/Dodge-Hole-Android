package com.threepartnersinteractive.dodgehole.world;

import com.threepartnersinteractive.dodgehole.DodgeHole;
import com.threepartnersinteractive.dodgehole.MyRandom;
import com.threepartnersinteractive.dodgehole.entities.Cow;
import com.threepartnersinteractive.dodgehole.entities.House;
import com.threepartnersinteractive.dodgehole.entities.Salt;
import com.threepartnersinteractive.dodgehole.entities.Tree;
import com.threepartnersinteractive.dodgehole.entities.vehicles.Motorhome;
import com.threepartnersinteractive.dodgehole.entities.vehicles.Truck;
import com.threepartnersinteractive.dodgehole.resources.Images;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Path {

    private Town latest_added_town; //used to determine if the next town to be added is neighbor of the latest added town
    private Iterator<Town> iter;
    private Set<Town> path;

    public static Town[] towns = {new Town("Aguada"), //0
            new Town("Rincón"), //1
            new Town("Añasco"), //2
            new Town("Moca", 5), //3
            new Town("San Sebastían", 5), //4
            new Town("Las Marías"), //5
            new Town("Maricao"), //6
            new Town("Mayaguez"), //7
            new Town("Hormigueros"), //8
            new Town("Cabo Rojo"), //9
            new Town("Lajas"), //10
            new Town("San German"), //11
            new Town("Sabana Grande"), //12
            new Town("Guánica"), //13
            new Town("Yauco"), //14
            new Town("Guayanilla"), //15
            new Town("Peñuelas"), //16
            new Town("Ponce"), //17
            new Town("Juana Díaz"), //18
            new Town("Villalba"), //19
            new Town("Coamo"), //20
            new Town("Santa Isabel"), //21
            new Town("Salinas"), //22
            new Town("Guayama"), //23
            new Town("Arroyo"), //24
            new Town("Patillas"), //25
            new Town("Maunabo"), //26
            new Town("Yabucoa"), //27
            new Town("San Lorenzo"), //28
            new Town("Caguas"), //29
            new Town("Gurabo"), //30
            new Town("Juncos"), //31
            new Town("Las Piedras"), //32
            new Town("Humacao"), //33
            new Town("Naguabo"), //34
            new Town("Ceiba"), //35
            new Town("Fajardo"), //36
            new Town("Luquillo"), //37
            new Town("Río Grande"), //38
            new Town("Loíza"), //39
            new Town("Canóvanas"), //40
            new Town("Carolina"), //41
            new Town("Trujillo Alto"), //42
            new Town("San Juan"), //43
            new Town("Guaynabo"), //44
            new Town("Cataño"), //45
            new Town("Bayamón"), //46
            new Town("Aguas Buenas"), //47
            new Town("Cidra"), //48
            new Town("Cayey"), //49
            new Town("Aibonito"), //50
            new Town("Barranquitas"), //51
            new Town("Comerío"), //52
            new Town("Naranjito"), //53
            new Town("Corrozal"), //54
            new Town("Toa Alta"), //55
            new Town("Toa Baja"), //56
            new Town("Dorado"), //57
            new Town("Vega Alta"), //58
            new Town("Vega Baja"), //59
            new Town("Manatí"), //60
            new Town("Barceloneta"), //61
            new Town("Arecibo"), //62
            new Town("Florida"), //63
            new Town("Ciales"), //64
            new Town("Morovis"), //65
            new Town("Orocovis"), //66
            new Town("Jayuya"), //67
            new Town("Utuado", 5), //68
            new Town("Adjuntas", 5), //69
            new Town("Lares"), //70
            new Town("Hatillo"), //71
            new Town("Camuy"), //72
            new Town("Quebradillas"), //73
            new Town("Isabela"), //74
            new Town("Aguadilla") /*75*/};

    public Path() {
        this.path = new LinkedHashSet<>();
        this.addHazards();
        this.addNeighbors();

        while (path.size() < 40) {
            path.clear();
            createPath(towns[MyRandom.nextInt(76)]);
        }

        this.iter = path.iterator();
    }

    private void addNeighbors() {
        //Aguada
        towns[0].getNeighbors().add(towns[1]); //ADDS RINCON
        towns[0].getNeighbors().add(towns[2]); //ADDS ANASCO
        towns[0].getNeighbors().add(towns[75]); //ADDS AGUADILLA
        towns[0].getNeighbors().add(towns[3]); //ADDS MOCA

        //Rincon
        towns[1].getNeighbors().add(towns[0]); //ADDS AGUADA
        towns[1].getNeighbors().add(towns[2]); //ADDS ANASCO

        //Añasco
        towns[2].getNeighbors().add(towns[0]); //ADDS AGUADA
        towns[2].getNeighbors().add(towns[1]); //ADDS RINCON
        towns[2].getNeighbors().add(towns[3]); //ADDS MOCA
        towns[2].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[2].getNeighbors().add(towns[5]); //ADDS LAS MARIAS
        towns[2].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ

        //Moca
        towns[3].getNeighbors().add(towns[0]); //ADDS AGUADA
        towns[3].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[3].getNeighbors().add(towns[74]); //ADDS ISABELA
        towns[3].getNeighbors().add(towns[75]); //ADDS AGUADILLA
        towns[3].getNeighbors().add(towns[2]); //ADDS ANASCO

        //San Sebastian
        towns[4].getNeighbors().add(towns[3]); //ADDS MOCA
        towns[4].getNeighbors().add(towns[74]); //ADDS ISABELA
        towns[4].getNeighbors().add(towns[73]); //ADDS QUEBRADILLAS
        towns[4].getNeighbors().add(towns[70]); //ADDS LARES
        towns[4].getNeighbors().add(towns[5]); //ADDS LAS MARIAS
        towns[4].getNeighbors().add(towns[2]); //ADDS ANASCO

        //Las Marias
        towns[5].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[5].getNeighbors().add(towns[70]); //ADDS LARES
        towns[5].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[5].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ
        towns[5].getNeighbors().add(towns[2]); //ADDS ANASCO

        //Maricao
        towns[6].getNeighbors().add(towns[70]); //ADDS LARES
        towns[6].getNeighbors().add(towns[5]); //ADDS LAS MARIAS
        towns[6].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ
        towns[6].getNeighbors().add(towns[11]); //ADDS SAN GERMAN
        towns[6].getNeighbors().add(towns[12]); //ADDS SABANA GRANDE
        towns[6].getNeighbors().add(towns[14]); //ADDS YAUCO

        //Mayaguez
        towns[7].getNeighbors().add(towns[2]); //ADDS ANASCO
        towns[7].getNeighbors().add(towns[5]); //ADDS LAS MARIAS
        towns[7].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[7].getNeighbors().add(towns[8]); //ADDS HORMIGUEROS
        towns[7].getNeighbors().add(towns[9]); //ADDS CABO ROJO
        towns[7].getNeighbors().add(towns[11]); //ADDS SAN GERMAN

        //Hormigueros
        towns[8].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ
        towns[8].getNeighbors().add(towns[9]); //ADDS CABO ROJO
        towns[8].getNeighbors().add(towns[11]); //ADDS SAN GERMAN

        //Cabo Rojo
        towns[9].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ
        towns[9].getNeighbors().add(towns[8]); //ADDS HORMIGUEROS
        towns[9].getNeighbors().add(towns[11]); //ADDS SAN GERMAN
        towns[9].getNeighbors().add(towns[10]); //ADDS LAJAS

        //Lajas
        towns[10].getNeighbors().add(towns[9]); //ADDS CABO ROJO
        towns[10].getNeighbors().add(towns[11]); //ADDS SAN GERMAN
        towns[10].getNeighbors().add(towns[12]); //ADDS SABANA GRANDE
        towns[10].getNeighbors().add(towns[13]); //ADDS GUANICA

        //San German
        towns[11].getNeighbors().add(towns[7]); //ADDS MAYAGUEZ
        towns[11].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[11].getNeighbors().add(towns[12]); //ADDS SABANA GRANDE
        towns[11].getNeighbors().add(towns[10]); //ADDS LAJAS
        towns[11].getNeighbors().add(towns[9]); //ADDS CABO ROJO
        towns[11].getNeighbors().add(towns[8]); //ADDS HORMIGUEROS

        //Sabana Grande
        towns[12].getNeighbors().add(towns[11]); //ADDS SAN GERMAN
        towns[12].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[12].getNeighbors().add(towns[14]); //ADDS YAUCO
        towns[12].getNeighbors().add(towns[13]); //ADDS GUANICA
        towns[12].getNeighbors().add(towns[10]); //ADDS LAJAS

        //Guanica
        towns[13].getNeighbors().add(towns[10]); //ADDS LAJAS
        towns[13].getNeighbors().add(towns[12]); //ADDS SABANA GRANDE
        towns[13].getNeighbors().add(towns[14]); //ADDS YAUCO

        //Yauco
        towns[14].getNeighbors().add(towns[13]); //ADDS GUANICA
        towns[14].getNeighbors().add(towns[12]); //ADDS SABANA GRANDE
        towns[14].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[14].getNeighbors().add(towns[69]); //ADDS ADJUNTAS
        towns[14].getNeighbors().add(towns[15]); //ADDS GUAYANILLA

        //Guayanilla
        towns[15].getNeighbors().add(towns[14]); //ADDS YAUCO
        towns[15].getNeighbors().add(towns[69]); //ADDS ADJUNTAS
        towns[15].getNeighbors().add(towns[16]); //ADDS PENUELAS

        //Peñuelas
        towns[16].getNeighbors().add(towns[15]); //ADDS GUAYANILLA
        towns[16].getNeighbors().add(towns[69]); //ADDS ADJUNTAS
        towns[16].getNeighbors().add(towns[17]); //ADDS PONCE

        //Ponce
        towns[17].getNeighbors().add(towns[16]); //ADDS PENUELAS
        towns[17].getNeighbors().add(towns[69]); //ADDS ADJUNTAS
        towns[17].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[17].getNeighbors().add(towns[67]); //ADDS JAYUYA
        towns[17].getNeighbors().add(towns[18]); //ADDS JUANA DIAZ

        //Juana Diaz
        towns[18].getNeighbors().add(towns[17]); //ADDS PONCE
        towns[18].getNeighbors().add(towns[19]); //ADDS VILLALBA
        towns[18].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[18].getNeighbors().add(towns[21]); //ADDS SANTA ISABEL

        //Villalba
        towns[19].getNeighbors().add(towns[18]); //ADDS JUANA DIAZ
        towns[19].getNeighbors().add(towns[66]); //ADDS OROCOVIS
        towns[19].getNeighbors().add(towns[20]); //ADDS COAMO

        //Coamo
        towns[20].getNeighbors().add(towns[19]); //ADDS VILLALBA
        towns[20].getNeighbors().add(towns[18]); //ADDS JUANA DIAZ
        towns[20].getNeighbors().add(towns[66]); //ADDS OROCOVIS
        towns[20].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS
        towns[20].getNeighbors().add(towns[50]); //ADDS AIBONITO
        towns[20].getNeighbors().add(towns[22]); //ADDS SALINAS
        towns[20].getNeighbors().add(towns[21]); //ADDS SANTA ISABEL

        //Santa Isabel
        towns[21].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[21].getNeighbors().add(towns[18]); //ADDS JUANA DIAZ
        towns[21].getNeighbors().add(towns[22]); //ADDS SALINAS

        //Salinas
        towns[22].getNeighbors().add(towns[21]); //ADDS SANTA ISABEL
        towns[22].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[22].getNeighbors().add(towns[50]); //ADDS AIBONITO
        towns[22].getNeighbors().add(towns[49]); //ADDS CAYEY
        towns[22].getNeighbors().add(towns[23]); //ADDS GUAYAMA

        //Guayama
        towns[23].getNeighbors().add(towns[22]); //ADDS SALINAS
        towns[23].getNeighbors().add(towns[49]); //ADDS CAYEY
        towns[23].getNeighbors().add(towns[25]); //ADDS PATILLAS
        towns[23].getNeighbors().add(towns[24]); //ADDS ARROYO

        //Arroyo
        towns[24].getNeighbors().add(towns[23]); //ADDS GUAYAMA
        towns[24].getNeighbors().add(towns[25]); //ADDS PATILLAS

        //Patillas
        towns[25].getNeighbors().add(towns[24]); //ADDS ARROYO
        towns[25].getNeighbors().add(towns[23]); //ADDS GUAYAMA
        towns[25].getNeighbors().add(towns[28]); //ADDS SAN LORENZO
        towns[25].getNeighbors().add(towns[27]); //ADDS YABUCOA
        towns[25].getNeighbors().add(towns[26]); //ADDS MAUNABO

        //Maunabo
        towns[26].getNeighbors().add(towns[25]); //ADDS PATILLAS
        towns[26].getNeighbors().add(towns[27]); //ADDS YABUCOA

        //Yabucoa
        towns[27].getNeighbors().add(towns[26]); //ADDS MAUNABO
        towns[27].getNeighbors().add(towns[25]); //ADDS PATILLAS
        towns[27].getNeighbors().add(towns[32]); //ADDS LAS PIEDRAS
        towns[27].getNeighbors().add(towns[33]); //ADDS HUMACAO
        towns[27].getNeighbors().add(towns[28]); //ADDS SAN LORENZO

        //San Lorenzo
        towns[28].getNeighbors().add(towns[27]); //ADDS YABUCOA
        towns[28].getNeighbors().add(towns[25]); //ADDS PATILLAS
        towns[28].getNeighbors().add(towns[30]); //ADDS GURABO
        towns[28].getNeighbors().add(towns[31]); //ADDS JUNCOS
        towns[28].getNeighbors().add(towns[32]); //ADDS LAS PIEDRAS
        towns[28].getNeighbors().add(towns[29]); //ADDS CAGUAS

        //Caguas
        towns[29].getNeighbors().add(towns[28]); //ADDS SAN LORENZO
        towns[29].getNeighbors().add(towns[49]); //ADDS CAYEY
        towns[29].getNeighbors().add(towns[35]); //ADDS CIDRA
        towns[29].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS
        towns[29].getNeighbors().add(towns[43]); //ADDS SAN JUAN
        towns[29].getNeighbors().add(towns[42]); //ADDS TRUJILLO ALTO
        towns[29].getNeighbors().add(towns[30]); //ADDS GURABO

        //Gurabo
        towns[30].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[30].getNeighbors().add(towns[42]); //ADDS TRUJILLO ALTO
        towns[30].getNeighbors().add(towns[41]); //ADDS CAROLINA
        towns[30].getNeighbors().add(towns[28]); //ADDS SAN LORENZO
        towns[30].getNeighbors().add(towns[31]); //ADDS JUNCOS

        //Juncos
        towns[31].getNeighbors().add(towns[30]); //ADDS GURABO
        towns[31].getNeighbors().add(towns[40]); //ADDS CANOVANAS
        towns[31].getNeighbors().add(towns[28]); //ADDS SAN LORENZO
        towns[31].getNeighbors().add(towns[32]); //ADDS LAS PIEDRAS

        //Las Piedras
        towns[32].getNeighbors().add(towns[31]); //ADDS JUNCOS
        towns[32].getNeighbors().add(towns[28]); //ADDS SAN LORENZO
        towns[32].getNeighbors().add(towns[34]); //ADDS NAGUABO
        towns[32].getNeighbors().add(towns[27]); //ADDS YABUCOA
        towns[32].getNeighbors().add(towns[33]); //ADDS HUMACAO

        //Humacao
        towns[33].getNeighbors().add(towns[32]); //ADDS LAS PIEDRAS
        towns[33].getNeighbors().add(towns[27]); //ADDS YABUCOA
        towns[33].getNeighbors().add(towns[34]); //ADDS NAGUABO

        //Naguabo
        towns[34].getNeighbors().add(towns[33]); //ADDS HUMACAO
        towns[34].getNeighbors().add(towns[32]); //ADDS LAS PIEDRAS
        towns[34].getNeighbors().add(towns[38]); //ADDS RIO GRANDE
        towns[34].getNeighbors().add(towns[35]); //ADDS CEIBA

        //Ceiba
        towns[35].getNeighbors().add(towns[34]); //ADDS NAGUABO
        towns[35].getNeighbors().add(towns[36]); //ADDS FAJARDO

        //Fajardo
        towns[36].getNeighbors().add(towns[35]); //ADDS CEIBA
        towns[36].getNeighbors().add(towns[37]); //ADDS LUQUILLO

        //Luquillo
        towns[37].getNeighbors().add(towns[36]); //ADDS FAJARDO
        towns[37].getNeighbors().add(towns[38]); //ADDS RIO GRANDE

        //Rio Grande
        towns[38].getNeighbors().add(towns[37]); //ADDS LUQUILLO
        towns[38].getNeighbors().add(towns[34]); //ADDS NAGUABO
        towns[38].getNeighbors().add(towns[40]); //ADDS CANOVANAS
        towns[38].getNeighbors().add(towns[39]); //ADDS LOIZA

        //Loiza
        towns[39].getNeighbors().add(towns[38]); //ADDS RIO GRANDE
        towns[39].getNeighbors().add(towns[41]); //ADDS CAROLINA
        towns[39].getNeighbors().add(towns[40]); //ADDS CANOVANAS

        //Canovanas
        towns[40].getNeighbors().add(towns[39]); //ADDS LOIZA
        towns[40].getNeighbors().add(towns[38]); //ADDS RIO GRANDE
        towns[40].getNeighbors().add(towns[31]); //ADDS JUNCOS
        towns[40].getNeighbors().add(towns[41]); //ADDS CAROLINA

        //Carolina
        towns[41].getNeighbors().add(towns[40]); //ADDS CANOVANAS
        towns[41].getNeighbors().add(towns[39]); //ADDS LOIZA
        towns[41].getNeighbors().add(towns[30]); //ADDS GURABO
        towns[41].getNeighbors().add(towns[43]); //ADDS SAN JUAN
        towns[41].getNeighbors().add(towns[42]); //ADDS TRUJILLO ALTO

        //Trujillo Alto
        towns[42].getNeighbors().add(towns[41]); //ADDS CAROLINA
        towns[42].getNeighbors().add(towns[30]); //ADDS GURABO
        towns[42].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[42].getNeighbors().add(towns[43]); //ADDS SAN JUAN

        //San Juan
        towns[43].getNeighbors().add(towns[42]); //ADDS TRUJILLO ALTO
        towns[43].getNeighbors().add(towns[41]); //ADDS CAROLINA
        towns[43].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[43].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS
        towns[43].getNeighbors().add(towns[44]); //ADDS GUAYNABO

        //Guaynabo
        towns[44].getNeighbors().add(towns[43]); //ADDS SAN JUAN
        towns[44].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS
        towns[44].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[44].getNeighbors().add(towns[45]); //ADDS CATANO

        //Cataño
        towns[45].getNeighbors().add(towns[44]); //ADDS GUAYNABO
        towns[45].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[45].getNeighbors().add(towns[56]); //ADDS TOA BAJA

        //Bayamon
        towns[46].getNeighbors().add(towns[45]); //ADDS CATANO
        towns[46].getNeighbors().add(towns[44]); //ADDS GUAYNABO
        towns[46].getNeighbors().add(towns[53]); //ADDS NARANJITO
        towns[46].getNeighbors().add(towns[56]); //ADDS TOA BAJA
        towns[46].getNeighbors().add(towns[55]); //ADDS TOA ALTA
        towns[46].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS

        //Aguas Buenas
        towns[47].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[47].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[47].getNeighbors().add(towns[43]); //ADDS SAN JUAN
        towns[47].getNeighbors().add(towns[44]); //ADDS GUAYNABO
        towns[47].getNeighbors().add(towns[52]); //ADDS COMERIO
        towns[47].getNeighbors().add(towns[48]); //ADDS CIDRA

        //Cidra
        towns[48].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS
        towns[48].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[48].getNeighbors().add(towns[50]); //ADDS AIBONITO
        towns[48].getNeighbors().add(towns[52]); //ADDS COMERIO
        towns[48].getNeighbors().add(towns[49]); //ADDS CAYEY

        //Cayey
        towns[49].getNeighbors().add(towns[48]); //ADDS CIDRA
        towns[49].getNeighbors().add(towns[29]); //ADDS CAGUAS
        towns[49].getNeighbors().add(towns[23]); //ADDS GUAYAMA
        towns[49].getNeighbors().add(towns[22]); //ADDS SALINAS
        towns[49].getNeighbors().add(towns[50]); //ADDS AIBONITO

        //Aibonito
        towns[50].getNeighbors().add(towns[49]); //ADDS CAYEY
        towns[50].getNeighbors().add(towns[48]); //ADDS CIDRA
        towns[50].getNeighbors().add(towns[22]); //ADDS SALINAS
        towns[50].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[50].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS

        //Barranquitas
        towns[51].getNeighbors().add(towns[50]); //ADDS AIBONITO
        towns[51].getNeighbors().add(towns[53]); //ADDS NARANJITO
        towns[51].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[51].getNeighbors().add(towns[54]); //ADDS CORROZAL
        towns[51].getNeighbors().add(towns[66]); //ADDS OROCOVIS
        towns[51].getNeighbors().add(towns[52]); //ADDS COMERIO

        //Comerio
        towns[52].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS
        towns[52].getNeighbors().add(towns[48]); //ADDS CIDRA
        towns[52].getNeighbors().add(towns[47]); //ADDS AGUAS BUENAS
        towns[52].getNeighbors().add(towns[53]); //ADDS NARANJITO

        //Naranjito
        towns[53].getNeighbors().add(towns[52]); //ADDS COMERIO
        towns[53].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[53].getNeighbors().add(towns[55]); //ADDS TOA ALTA
        towns[53].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS
        towns[53].getNeighbors().add(towns[54]); //ADDS CORROZAL

        //Corrozal
        towns[54].getNeighbors().add(towns[53]); //ADDS NARANJITO
        towns[54].getNeighbors().add(towns[58]); //ADDS VEGA ALTA
        towns[54].getNeighbors().add(towns[65]); //ADDS MOROVIS
        towns[54].getNeighbors().add(towns[66]); //ADDS OROCOVIS
        towns[54].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS
        towns[54].getNeighbors().add(towns[55]); //ADDS TOA ALTA

        //Toa Alta
        towns[55].getNeighbors().add(towns[54]); //ADDS CORROZAL
        towns[55].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[55].getNeighbors().add(towns[53]); //ADDS NARANJITO
        towns[55].getNeighbors().add(towns[57]); //ADDS DORADO
        towns[55].getNeighbors().add(towns[58]); //ADDS VEGA ALTA
        towns[55].getNeighbors().add(towns[56]); //ADDS TOA BAJA

        //Toa Baja
        towns[56].getNeighbors().add(towns[55]); //ADDS TOA ALTA
        towns[56].getNeighbors().add(towns[46]); //ADDS BAYAMON
        towns[56].getNeighbors().add(towns[45]); //ADDS CATANO
        towns[56].getNeighbors().add(towns[57]); //ADDS DORADO

        //Dorado
        towns[57].getNeighbors().add(towns[56]); //ADDS TOA BAJA
        towns[57].getNeighbors().add(towns[55]); //ADDS TOA ALTA
        towns[57].getNeighbors().add(towns[58]); //ADDS VEGA ALTA

        //Vega Alta
        towns[58].getNeighbors().add(towns[57]); //ADDS DORADO
        towns[58].getNeighbors().add(towns[54]); //ADDS CORROZAL
        towns[58].getNeighbors().add(towns[55]); //ADDS TOA ALTA
        towns[58].getNeighbors().add(towns[59]); //ADDS VEGA BAJA

        //Vega Baja
        towns[59].getNeighbors().add(towns[58]); //ADDS VEGA ALTA
        towns[59].getNeighbors().add(towns[65]); //ADDS MOROVIS
        towns[59].getNeighbors().add(towns[60]); //ADDS MANATI

        //Manati
        towns[60].getNeighbors().add(towns[59]); //ADDS VEGA BAJA
        towns[60].getNeighbors().add(towns[64]); //ADDS CIALES
        towns[60].getNeighbors().add(towns[63]); //ADDS FLORIDA
        towns[60].getNeighbors().add(towns[61]); //ADDS BARCELONETA

        //Barceloneta
        towns[61].getNeighbors().add(towns[60]); //ADDS MANATI
        towns[61].getNeighbors().add(towns[63]); //ADDS FLORIDA
        towns[61].getNeighbors().add(towns[62]); //ADDS ARECIBO

        //Arecibo
        towns[62].getNeighbors().add(towns[61]); //ADDS BARCELONETA
        towns[62].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[62].getNeighbors().add(towns[71]); //ADDS HATILLO
        towns[62].getNeighbors().add(towns[63]); //ADDS FLORIDA

        //Florida
        towns[63].getNeighbors().add(towns[62]); //ADDS ARECIBO
        towns[63].getNeighbors().add(towns[61]); //ADDS BARCELONETA
        towns[63].getNeighbors().add(towns[60]); //ADDS MANATI
        towns[63].getNeighbors().add(towns[64]); //ADDS CIALES

        //Ciales
        towns[64].getNeighbors().add(towns[63]); //ADDS FLORIDA
        towns[64].getNeighbors().add(towns[60]); //ADDS MANATI
        towns[64].getNeighbors().add(towns[66]); //ADDS OROCOVIS
        towns[64].getNeighbors().add(towns[67]); //ADDS JAYUYA
        towns[64].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[64].getNeighbors().add(towns[65]); //ADDS MOROVIS

        //Morovis
        towns[65].getNeighbors().add(towns[64]); //ADDS CIALES
        towns[65].getNeighbors().add(towns[54]); //ADDS CORROZAL
        towns[65].getNeighbors().add(towns[59]); //ADDS VEGA BAJA
        towns[65].getNeighbors().add(towns[66]); //ADDS OROCOVIS

        //Orocovis
        towns[66].getNeighbors().add(towns[65]); //ADDS MOROVIS
        towns[66].getNeighbors().add(towns[64]); //ADDS CIALES
        towns[66].getNeighbors().add(towns[54]); //ADDS CORROZAL
        towns[66].getNeighbors().add(towns[51]); //ADDS BARRANQUITAS
        towns[66].getNeighbors().add(towns[20]); //ADDS COAMO
        towns[66].getNeighbors().add(towns[19]); //ADDS VILLALBA

        //Jayuya
        towns[67].getNeighbors().add(towns[64]); //ADDS CIALES
        towns[67].getNeighbors().add(towns[17]); //ADDS PONCE
        towns[67].getNeighbors().add(towns[68]); //ADDS UTUADO

        //Utuado
        towns[68].getNeighbors().add(towns[67]); //ADDS JAYUYA
        towns[68].getNeighbors().add(towns[64]); //ADDS CIALES
        towns[68].getNeighbors().add(towns[62]); //ADDS ARECIBO
        towns[68].getNeighbors().add(towns[71]); //ADDS HATILLO
        towns[68].getNeighbors().add(towns[70]); //ADDS LARES
        towns[68].getNeighbors().add(towns[69]); //ADDS ADJUNTAS

        //Adjuntas
        towns[69].getNeighbors().add(towns[14]); //ADDS YAUCO
        towns[69].getNeighbors().add(towns[15]); //ADDS GUAYANILLA
        towns[69].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[69].getNeighbors().add(towns[16]); //ADDS PENUELAS
        towns[69].getNeighbors().add(towns[17]); //ADDS PONCE
        towns[69].getNeighbors().add(towns[70]); //ADDS LARES

        //Lares
        towns[70].getNeighbors().add(towns[69]); //ADDS ADJUNTAS
        towns[70].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[70].getNeighbors().add(towns[72]); //ADDS CAMUY
        towns[70].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[70].getNeighbors().add(towns[5]); //ADDS LAS MARIAS
        towns[70].getNeighbors().add(towns[6]); //ADDS MARICAO
        towns[70].getNeighbors().add(towns[71]); //ADDS HATILLO

        //Hatillo
        towns[71].getNeighbors().add(towns[70]); //ADDS LARES
        towns[71].getNeighbors().add(towns[68]); //ADDS UTUADO
        towns[71].getNeighbors().add(towns[62]); //ADDS ARECIBO
        towns[71].getNeighbors().add(towns[72]); //ADDS CAMUY

        //Camuy
        towns[72].getNeighbors().add(towns[71]); //ADDS HATILLO
        towns[72].getNeighbors().add(towns[70]); //ADDS LARES
        towns[72].getNeighbors().add(towns[73]); //ADDS QUEBRADILLAS

        //Quebradillas
        towns[73].getNeighbors().add(towns[72]); //ADDS CAMUY
        towns[73].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[73].getNeighbors().add(towns[74]); //ADDS ISABELA

        //Isabela
        towns[74].getNeighbors().add(towns[73]); //ADDS QUEBRADILLAS
        towns[74].getNeighbors().add(towns[4]); //ADDS SAN SEBASTIAN
        towns[74].getNeighbors().add(towns[3]); //ADDS MOCA
        towns[74].getNeighbors().add(towns[75]); //ADDS AGUADILLA

        //Aguadilla
        towns[75].getNeighbors().add(towns[74]); //ADDS ISABELA
        towns[75].getNeighbors().add(towns[3]); //ADDS MOCA
        towns[75].getNeighbors().add(towns[0]); //ADDS AGUADA
    }

    private void addHazards() {
        /*Hatillo cows*/
        for (int i = 0; i < 10; i++) {
            towns[71].addDynamicHazard(new Cow(146, DodgeHole.HEIGHT), 1, 3);
        }

        /*San Juan trucks*/
        for (int i = 0; i < 10; i++) {
            towns[43].addDynamicHazard(new Truck(145 + MyRandom.nextInt(181), -Images.truck.getRegionHeight()), 2, 5);
        }

        /*Cabo Rojo motorhomes*/
        for (int i = 0; i < 10; i++) {
            towns[9].addDynamicHazard(new Motorhome(145 + MyRandom.nextInt(181), -Images.motorhome.getRegionHeight()), 3, 5);
        }
    }

    private void addStaticHazards(Town current_town) {

        if(current_town.getName().equals("Cabo Rojo")){
            for (int i = 0; i < 30; i++){
                    int random = MyRandom.nextInt(3);
                    // 0 is for tree, 1 is for house, 2 is for salt

                    if (random == 0) {
                        current_town.addStaticHazard(new Tree(MyRandom.nextInt(30), DodgeHole.HEIGHT), 0.5, 0.5);
                    }
                    else if(random == 1){
                        current_town.addStaticHazard(new House(0, DodgeHole.HEIGHT, true), 0.5, 0.5);
                    }
                    else{
                        current_town.addStaticHazard(new Salt(0, DodgeHole.HEIGHT), 0.5, 0.5);
                    }


            }
        }
        else{
            for (int i = 0; i < 30; i++) {
                int random = MyRandom.nextInt(2);
                // 0 is for tree, 1 is for house

                if (random == 0) {
                    current_town.addStaticHazard(new Tree(MyRandom.nextInt(30), DodgeHole.HEIGHT), 0, 0);
                }
                else {
                    current_town.addStaticHazard(new House(0, DodgeHole.HEIGHT, true), 0, 0);
                }

                random = MyRandom.nextInt(2);

                if (random == 0)
                    current_town.addStaticHazard(new Tree(382 + MyRandom.nextInt(30), DodgeHole.HEIGHT), 0, 0);
                else {
                    current_town.addStaticHazard(new House(390, DodgeHole.HEIGHT, false), 0, 0);
                }
            }
        }

    }

    public void createPath(Town town) {
        path.add(town);
        latest_added_town = town;

        int i = MyRandom.nextInt(town.getNeighbors().size());
        int j = 0;

        while (j++ < town.getNeighbors().size()) {
            Town neighbor = town.getNeighbors().get(i);

            if (!path.contains(neighbor) && latest_added_town.getNeighbors().contains(neighbor)) {
                createPath(neighbor);
            }

            i = (i + 1) % town.getNeighbors().size();
        }
    }

    public Town moveTown(){
        if(!iter.hasNext()){
            this.createPath(latest_added_town.getNeighbors().get(MyRandom.nextInt(latest_added_town.getNeighbors().size())));
        }

        Town next = iter.next();
        addStaticHazards(next); //TODO
        return next;
    }
}
