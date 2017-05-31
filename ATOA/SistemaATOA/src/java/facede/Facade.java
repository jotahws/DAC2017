/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facede;

import beans.TipoAtividade;
import daos.TipoAtividadeDAO;
import java.sql.SQLException;

/**
 *
 * @author MauMau
 */
public class Facade {

    public void insereTipo(TipoAtividade tipoAtv) throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        dao.inserirTipo(tipoAtv);
    }
    
}
