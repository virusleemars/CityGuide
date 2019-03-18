package com.cityguide.core;


// Географические координаты
public class GeoCoord {
    private static final double FI_MIN = -90.;
    private static final double FI_MAX =  90.;
    private static final double LAMBDA_MIN = -180.;
    private static final double LAMBDA_MAX = 180.;

    private static final long HASH_INIT = 23;
    private static final long HASH_CONST = 37;

    private double fi; // Широта  (от -90 до +90)
    private double lambda; // Долгота (от -180 до +180)

    /*Установить координаты с проверкой диапазона*/
    public void setFL(double f, double l) throws GeoCoordException {
        if ((f >= FI_MIN) && (f <= FI_MAX) && (l >= LAMBDA_MIN) && (l <= LAMBDA_MAX)) {
            fi = f;
            lambda = l;
        }
        else throw new GeoCoordException("Некорректное значение кординат ", f, l);
    }

    public double getF() {
        return fi;
    }

    public double getL() {
        return lambda;
    }

    @Override
    public String toString() {
        return "GeoCoord{" + "fi=" + fi + ", lambda=" + lambda + '}';
    }

    /*Вывод координат в формате  */
    public String toStringFormat(){
        return Math.abs(fi) + ((fi < 0) ? " Ю.Ш." : " С.Ш." ) + " " +
               Math.abs(lambda) + ((lambda < 0) ? " З.Д." : " В.Д." );
    }

    @Override
    public int hashCode() {
        long result = HASH_INIT;

        result = HASH_CONST * result + Double.hashCode(fi);
        result = HASH_CONST * result + Double.hashCode(lambda);

        return Long.valueOf(result).intValue();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false; // нет объекта
        if (!(object instanceof GeoCoord)) return false; // объект не геокоординаты
        if (this == object) return true; // ссылается на тот же объект

        return ((Double)fi).equals(((GeoCoord) object).fi) &&
               ((Double)lambda).equals(((GeoCoord) object).lambda); // проверим эквивалентность координат
    }


}
