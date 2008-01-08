package planetekids.beans.stateful;

import java.util.List;
import javax.ejb.Remote;
import planetekids.beans.entity.ProductBean;

@Remote
public interface CartRemote {
    public void init();
    
    public void flushCart() throws Exception;
    public int validateCart() throws Exception;
    public List<ProductBean> getCartProducts()  throws Exception;
    public void setCartProductNumber(int product_id, int product_number) throws Exception;
    public int getCartProductNumber(int product_id) throws Exception;
}
