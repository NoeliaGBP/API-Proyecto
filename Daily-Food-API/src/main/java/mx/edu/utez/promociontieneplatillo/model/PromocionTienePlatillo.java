package mx.edu.utez.promociontieneplatillo.model;

import mx.edu.utez.platillo.model.Platillo;
import mx.edu.utez.promocion.model.Promocion;

public class PromocionTienePlatillo {
    private Promocion idPromocion;
    private Platillo idPlatillo;

    public Promocion getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Promocion idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }
}
