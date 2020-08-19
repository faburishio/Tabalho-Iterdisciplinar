package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import util.ConectaBanco;


public class ItensDAO {
 
    private Connection con;

    public ItensDAO() {
        this.con = new ConectaBanco().getConnection();
    }

    public void cadastrar(Item p) throws SQLException {
        
        String query;
        query = "INSERT INTO tbitens (nome, categoria, valor, descricao) VALUES (?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, p.getNomeItem());
        st.setString(2, p.getCatItem() );
        st.setDouble(3, p.getValItem());
        st.setString(4, p.getDescItem());
        st.execute();
        st.close();
        con.close();
    }
    
    public List<Item> pesquisar() throws SQLException, Exception {
        
        List<Item> lista = new ArrayList<>();
        String query = "SELECT * FROM tbproduto";
        
        PreparedStatement st = con.prepareStatement(query);
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()) {            
            Item itm = new Item();
            
            itm.setIditem       ( rs.getInt   ("id")  );
            itm.setNomeItem     ( rs.getString("nome")  );
            itm.setCatItem      (rs.getString("categoria"));
            itm.setValItem      (rs.getDouble("valor"));
            itm.setDescItem     (rs.getString("descricao"));
            
            lista.add(itm);            
        }   
        return lista;
    }

    public void atualizar(Item p) throws SQLException {
        
        String query;
        query = "UPDATE tbitens set nome = ?, categoria= ?, valor= ?, descricao= ? WHERE idItm = ?";

        PreparedStatement st = con.prepareStatement(query);
        st.setString (1, p.getNomeItem());
        st.setString (2, p.getCatItem() );
        st.setDouble (3, p.getValItem());
        st.setString (4, p.getDescItem());
        st.setInt    (5, p.getIditem());
        st.execute();
        st.close();
        con.close();
    }

    public void deletar(Item p) throws SQLException {
        
        String query;
        query = "DELETE FROM tbitens WHERE idItm = ?;";

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, p.getIditem());
        st.execute();
        st.close();
        con.close();
    }

}