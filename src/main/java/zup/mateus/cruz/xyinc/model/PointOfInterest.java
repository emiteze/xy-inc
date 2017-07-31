package zup.mateus.cruz.xyinc.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.UUID;

public class PointOfInterest implements Serializable{

    @Id
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = "O nome do ponto deve ser preenchido")
    private String name;

    @Min(value = 0, message = "Coordenada X não pode ser negativa")
    private int coordx;

    @Min(value = 0, message = "Coordenada Y não pode ser negativa")
    private int coordy;

    public PointOfInterest(String name, int coordx, int coordy){
        this.name = name;
        this.coordx = coordx;
        this.coordy = coordy;
    }

    public PointOfInterest() {}

    public UUID getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoordx() {
        return coordx;
    }

    public void setCoordx(int coordx) {
        this.coordx = coordx;
    }

    public int getCoordy() {
        return coordy;
    }

    public void setCoordy(int coordy) {
        this.coordy = coordy;
    }

    public double distanceBetweenPoints(PointOfInterest anotherPoint){
        return Math.sqrt(Math.pow((this.coordx-anotherPoint.getCoordx()), 2) + Math.pow((this.coordy-anotherPoint.getCoordy()), 2));
    }

    @Override
    public String toString(){
        return "POI=[" + name + ", (x=" + coordx + ", y=" + coordy + ")]";
    }

}
