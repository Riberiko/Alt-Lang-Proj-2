import lombok.*;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cell{
    @Getter
    @Setter
    private String oem;
    @Getter
    @Setter
    String model;
    @Getter
    @Setter
    private int launch_announced;
    @Getter
    @Setter
    private int launch;
    @Getter
    @Setter
    private String launch_status;
    @Getter
    @Setter
    private String body_dimensions;
    @Getter
    @Setter
    private float body_weight;
    @Getter
    @Setter
    private String body_sim;
    @Getter
    @Setter
    private String display_type;
    @Getter
    @Setter
    private float display_size;
    @Getter
    @Setter
    private String display_resolution;
    @Getter
    @Setter
    private String features_sensors;
    @Getter
    @Setter
    private String platform_os;

    public String toString()
    {
        String str = "";

        str += "Cell Phone -> {\n";
        str += "\tOEM : '" + getOem() + "',\n";
        str += "\tModel : '" + getModel() + "',\n";
        str += "\tLaunch Announced : " + launch_announced + ",\n";
        str += "\tLaunch Year : " + ((launch != 0) ? launch : "NULL") + ",\n";
        str += "\tLaunch Status : '" + launch_status + "',\n";
        str += "\tBody Dimensions : '" + body_dimensions + "',\n";
        str += "\tBody Weight : " + body_weight + ",\n";
        str += "\tBody Sim : '" + body_sim + "',\n";
        str += "\tDisplay Type : '" + display_type + "'\n";
        str += "\tDisplay Size : " + display_size + ",\n";
        str += "\tDisplay Resolution : '" + display_resolution + "',\n";
        str += "\tFeatures Sensors : '" + features_sensors + "',\n";
        str += "\tPlatform Os : '" + platform_os + "'\n}";
        return str;
    }
}
