package vacinacovid.daos;

/**
 *
 * @author maxwell
 */
public interface DataBasePersistense {
    public void insert();
    public void update();
    public void delete();
    public boolean dadosValidos();
    public void limparDados();
}
