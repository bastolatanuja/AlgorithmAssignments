package week7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BurnTreeTest {

	@Test
	void test1() {
			BurnTree.Node root = null;
		    root = BurnTree.insert(root, 50);
		    BurnTree.insert(root, 30);
		    BurnTree.insert(root, 20);
		    BurnTree.insert(root, 40);
		    BurnTree.insert(root, 70);
		    BurnTree.insert(root, 60);
		    BurnTree.insert(root, 80);
		 
		   
		    BurnTree.inorder(root);
		    int res=BurnTree.check();
		    
		    assertEquals(res,2);
	}
	@Test
	void test2() {
			BurnTree.Node root = null;
		    root = BurnTree.insert(root, 50);
		    BurnTree.insert(root, 30);
		    BurnTree.insert(root, 20);
		    BurnTree.insert(root, 40);
		    BurnTree.insert(root, 70);
		    BurnTree.insert(root, 60);
		    BurnTree.insert(root, 80);
		    BurnTree.insert(root, 120);
		    BurnTree.insert(root, 130);
		    
		 
		   
		    BurnTree.inorder(root);
		    int res=BurnTree.check();
		    
		    assertEquals(res,3);
	}

}