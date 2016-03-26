/*		CSC 244
 * 	TakeHomeExam 1
 *  Name: Bhargi Patel
 *  Sac State ID - 217314540  
 */

import java.util.Arrays;
import java.util.Comparator;

public class SortJoin {

	// Declaration of Relation S
	Integer [] S = { 3,4, 2,7, 4,5 };
		
	// Declaration of Relation R
	Integer [] R = { 2,3,4, 2,4,4, 2,2,7, 5,4,7, 2,3,4, 5,6,7, 8,9,10 };
		
	// output is the output buffer for the disk storage
	Integer [][]output= new Integer[50][4];
	
	//Declaration of Relation R sublists of form R(X,Y,Z)
	Integer [][]R1 = new Integer[7][3];
	
	//Declaration of Relation S sublists of form S(Z,W)
	Integer [][]S1 = new Integer[3][2];
	
	//Index for output[][]
	Integer index = 0;
	
	//Sort Function for Relation R; Sort key :Z
	public static Integer[][] SortRelR(Integer[][] ar) {
        Arrays.sort(ar, new Comparator<Integer[]>() {
            public int compare(Integer[] int1, Integer[] int2) {
                Integer numOfKeys1 = int1[2];
                Integer numOfKeys2 = int2[2];
                return numOfKeys1.compareTo(numOfKeys2);
            }
        });
        return ar;
    }
	
	//Sort Function for Relation S; Sort Key :Z
	public static Integer[][] SortRelS(Integer[][] ar) {
        Arrays.sort(ar, new Comparator<Integer[]>() {
            public int compare(Integer[] int1, Integer[] int2) {
                Integer numOfKeys1 = int1[0];
                Integer numOfKeys2 = int2[0];
                return numOfKeys1.compareTo(numOfKeys2);
            }
        });
        return ar;
    }
	
	@SuppressWarnings("unused")
	public void sortJoin()
	{
		int in1 = 0, in2 = 0;
		
		//Creating Sublists for Relation R: one tuple per block
		for(int i=0;i<R1.length;i++)
		{
			for (int j=0;j<R1[i].length;j++)
			{
				R1[i][j] = R[in1++];
			}
		}
		//Creating Sublists for Relation S: one tuple per block
		for(int i=0;i<S1.length;i++)
		{
			for (int j=0;j<S1[i].length;j++)
			{
				S1[i][j] = S[in2++];
			}
		}
		
		
		//Display Sublists for both R and S relations
		System.out.print("Displaying Sublists of R\n");
		for(int i = 0; i < (R1.length); i++)
		{
			for(int j = 0; j < (R1[i].length); j++)
			{
				System.out.print(R1[i][j] + ",");
				//System.out.print("\n");
			}
			System.out.print("\n");
		}
		System.out.print("Displaying Sublists of S\n");
		for(int i = 0; i < (S1.length); i++)
		{
			for(int j = 0; j < (S1[i].length); j++)
			{
				System.out.print(S1[i][j] + ",");
				//System.out.print("\n");
			}
			System.out.print("\n");
		}
		
		//Call the Sort Function to Sort the Sublists
		R1 = SortRelR(R1);
		S1 = SortRelS(S1);
		
		//Print Sorted Sublists
		System.out.print("Sublists of R in Sorted order\n");
		for(int i = 0; i < (R1.length); i++)
		{
			for(int j = 0; j < (R1[i].length); j++)
			{
				System.out.print(R1[i][j] + ",");
				//System.out.print("\n");
			}
			System.out.print("\n");
		}
		System.out.print("Sublists of S in Sorted order\n");
		for(int i = 0; i < (S1.length); i++)
		{
			for(int j = 0; j < (S1[i].length); j++)
			{
				System.out.print(S1[i][j] + ",");
				//System.out.print("\n");
			}
			System.out.print("\n");
		}
		
		
		//Comparing 'Z' value of Relations R and S to Join tuples
		System.out.print("Join of R and S, based on Z-value:\n");
		for(int i=0;i<R1.length;i++)
		{
			for(int j=0;j<R1[i].length; j++)
			{
				for(int x=0;x<S1.length;x++)
				{
					for (int y=0;y<S1[x].length;y++)
					{
						if(R1[i][2] == S1[x][0])
						{
							//Store output to output Buffer	
							output[index][0] = R1[i][0];
							output[index][1] = R1[i][1];
							output[index][2] = S1[x][0];
							output[index][3] = S1[x][1];
							index++ ;
							//System.out.print(R1[i][0]+","+R1[i][1]+","+S1[x][0]+","+S1[x][1]);
							break;
						}
					}
				}
				break;
			}
		}
	
		//Print the content of the output buffer to Disk
		Integer [][]diskOut = new Integer [50][4];
		System.arraycopy(output,0,diskOut,0,output.length);
		
		//Display content of Disk for final output
		for(int i=0;i<index;i++)
		{
			for(int j=0;j<diskOut[i].length;j++)
			{
				System.out.print(diskOut[i][j] + ",");
			}
			System.out.print("\n");
		}
	} // end of sortJoin()
} //end of class
