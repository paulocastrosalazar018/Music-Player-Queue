package questions;

import doNotModify.Song;
import doNotModify.SongNode;
import java.util.HashSet;

public class PlayQueue {
    public SongNode start; // DO NOT MODIFY
    public SongNode end;   // DO NOT MODIFY
    // You may add extra attributes here
    public SongNode middle;
    public int size = 0;
    /**
     * Adds a Song to the end of the PlayQueue.
     * <p>
     * Note: This must be completed before moving onto any other method.
     * @param song - The Song to add
     */
    public void addSong(Song song) {
        // TODO: To be completed
    	// check if the start is null
    	if(start == null && size == 0) {
    		// if it is then our queue is empty 
    		// add node to start 
    		start = new SongNode(song,null,null);
    		size++;
    		// make the end equals to start
    		end = start;
    		middle = start;
    	}else {
    		// if our queue is not empty
    		// add song to the end
    		end.next = new SongNode(song,null,end);
    		size++;		
    		// shift end to be this new node
    		end = end.next;
    		if(size < 3) {
    			middle = start;
    		}else if(size >=3) {
    			middle= middle.next;
    		}
    	}
    }

    /**
     * Remove the first SongNode with the parameter Song from the PlayQueue.
     * <p>
     * Return true if a SongNode was removed, false otherwise.
     * @param song
     * @return - true if a SongNode was removed, false otherwise.
     */
    public boolean removeSong(Song song) {
    	// TODO: To be completed
    	// is the list not empty
    	if(start != null && song != null && size > 0) {	
    		
    		if(size == 1 &&  song.equals(start.song)) {
    			start = null;
    			end = null;
    			middle = null;
    			return true;
    		}
    		SongNode temp = start;
    		while(temp.next != null && !song.equals(temp.song)) {
    		
    			temp = temp.next;
    		}
    		if(!song.equals(temp.song)){
    			return false;
    		}
    		if(temp == start) {
    			start = start.next;
    			start.previous = null;
    			middle = middle.previous;
    			size--;
    			return true;
    		}
    		if(temp == end) {
    			end = end.previous;
    			end.next = null;
    			middle = middle.previous;
    			size--;
    			return true;
    		}
    		temp.previous.next = temp.next;
    		temp.next.previous = temp.previous;
    		middle = middle.previous;
    		
    		size--;
    		return true;
    	}
        return false;  
    }

    /**
     * Removes the SongNode at the specified index from the PlayQueue, returning
     * the Song that was removed.
     * <p>
     * Return null if `index` is invalid.
     * @param index
     */
    public Song removeSong(int index) {
    	// TODO: To be completed
    	Song s = null;
    	if(start != null && size > 0 && index >=0) {
    		if(index >= size) {
    			return s;
    		}
    		// there is only one node in the list 
    		if(size == 1 && index == 0){
    			start = null;
    			end = null;
    			middle = null;
    			return s;
    		}
    		SongNode temp = start;
    		int i =0;
    		while(i != index) {
    			temp = temp.next;
    			i++;
    		}
    		// we have found the node at the index node
    		// if the index is the first node
    		s = temp.song;
    		if(temp == start) {
    			start = start.next;
    			start.previous = null;
    			middle = middle.previous;
    			size--;
    			return s;
    		}
    		if(temp == end) {
    			end = end.previous;
    			end.next = null;
    			middle = middle.previous;
    			size--;
    			return s;
    		}
    		temp.previous.next = temp.next;
    		temp.next.previous = temp.previous;
    		middle = middle.previous;
    		size--;
    		return s;
    		
    	}
        return s;  
    }

    /**
     * Return the size (number of SongNodes) in the PlayQueue.
     * @return the size of the PlayQueue
     */
    public int size() {
        return size;  // TODO: To be completed
    }

    /**
     * Reverse the calling object PlayQueues Song ordering.
     */
    public void reverseQueue() {
        // TODO: To be completed
    	
    	if(start != null && end != null && size > 1) {
    		if(size <= 1)
    			return;
    		
    		// make the start the end
    		start = end;
    		// end move backward
    		end = end.previous;
    		
    		// temp value to traverse pointing to start
    		SongNode temp = start;
    		// traverse backwards until reaching null
    		while(end.previous != null) {
    			// move end back
    			end = end.previous;
    			// hold previous end
        		SongNode prev = end.next;
        		// switch variables
    			temp.next = end.next;
    			prev.previous = temp;
    			// move temp to next
    			temp = temp.next;
    	
    			
    		}
    		start.previous = null;
    		end.next = null;
    		temp.next = end;
    		end.previous = temp;
    		
    	}
    }

    /**
     * Move the SongNode from the `fromIndex` index the specified `amount`.
     * 
     * Let the queue be:
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * Let fromIndex be 1.
     * The expected queue should be as follows for:
     * amount := 0
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * amount := 1
     *       start              end
     *         |                 |
     * null <- a <-> c <-> b <-> d -> null
     * 
     * amount := -1
     *       start              end
     *         |                 |
     * null <- b <-> a <-> c <-> d -> null
     * 
     * amount := 2
     *       start              end
     *         |                 |
     * null <- a <-> c <-> d <-> b -> null
     * <p>
     * Do nothing if either `fromIndex` is invalid, or `amount` is invalid for
     * the given `fromIndex`.
     * <p>
     * Do not create any new SongNode instances.
     * @param fromIndex
     * @param amount
     */
    public void moveSong(int fromIndex, int amount) {
        // TODO: To be completed
    	
    	// there should be at least 2 nodes in the list
    	if(start != null && size > 1) {
    		
    		// index has to be bigger than 0 and index has to be smaller than size

        	if(fromIndex < size && fromIndex >= 0) 
        	{
        		int Prange = (size - 1) - fromIndex;
        		int Nrange = Prange - (size - 1);
        		if(amount == 0 || amount > Prange || amount < Nrange)
        			return;

    			SongNode from = start;
    			int i = 0;
    			// find the from index node
    			while(i != fromIndex) {
    				from = from.next;
    				i++;
    			}
    			// temp is the fromIndex
    			// now lets find the toIndex
    			int x = 0;
    			SongNode to = from;
    			// we have found the from index
    			if(amount > x) {
    				// we are moving foward
    				while( x != amount) {
        				to = to.next;
        				x++;
        				// we have reach the to index
        			}
    				// is the fromIndex the start node
        			if(from == start) {        				
        				start= start.next;
        				start.previous = null;
        			}else {
        				// remove connection to from node
            			from.previous.next = from.next;
            			from.next.previous = from.previous;
            			// make new connection 
        			}
    				//we moving to the end node
    				if(to == end) {
    					to.next = from;
    					from.previous = to;
    					from.next = null;
    					end = end.next;
    				}
    				else {
    					to.next.previous = from;
    					from.next = to.next;
    					to.next = from;
    					from.previous = to;
    				}
    				return;     			
    			}
    			else if(amount < x) 
    			{
    				// we are moving backwards
    				while(x != amount) {
    					to = to.previous;
    					x--;
    				}
    				// we are moving the end node
    				if(from == end) {
    					end = end.previous;
    					end.next = null;
    					from.next = to;
    				}else {
    					from.previous.next = from.next;
    					from.next.previous = from.previous;
    					from.next = to;   					
    				}
    				if(to == start) {
    					from.previous = null;
    					to.previous = from;
    					start = from;		
    				}else {
    					to.previous.next = from;
    					from.previous = to.previous;
    					to.previous = from;
    				}
    			}	
        	}
    	}
    	
    }

    /**
     * Swap the SongNodes at parameter indices.
     * Do nothing if either parameters are invalid.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapSongs(int firstIndex, int secondIndex) {
        // TODO: To be completed
    	if(start != null) {
    		boolean inRange = (firstIndex >= 0 && secondIndex >= 0) ? true: false;
    		boolean inRange2 = (firstIndex < size  && secondIndex < size) ? true: false;   		
    		if(inRange && inRange2) {
    			if(firstIndex == secondIndex ) return;
    			SongNode temp = start;
    			SongNode y = null;
    			SongNode nextY = null;
    			
    			int i= 0;
    			int a = (firstIndex > secondIndex) ? firstIndex: secondIndex;
    			int b = (firstIndex < secondIndex) ? firstIndex: secondIndex;
    			// search through the list untill we find the node at a index
    			while(i != a) {
    				// we have found the node at b index
    				if(i == b) {
    					// save b index node in y
    					y = temp;
    					
    					//if the node at b index is the start node
    					if(b == 0) {
    						// move the start node 
    						start = start.next;
    						
    					}else {
    					// remove connections to b node
    					temp.previous.next = temp.next;
    					temp.next.previous = temp.previous;
    					
    					}
    					nextY = y.next;
    					// reduce size because we have remove one node
    					size--;
    				}	
    				// keep looking for node at a index
    				temp = temp.next;		
    				i++;
    			}
    			// we have found the two nodes that we have shuffle
    			
    			
    			// insert the b node using a node connections
    			// if the a node is the end node
    			if(temp == end) {
    				// insert the b node using the end connections
    				end.previous.next = y;
    				y.previous = end.previous;
    				y.next = null;
    				// make the end node the b node
    				end =  y;
    			}else {
    				// moving somewhere in the list that its not the end
    				// use the connections of a node to insert b 
    				temp.previous.next = y;
    				temp.next.previous = y;
    				
    				y.next = temp.next;
    				y.previous = temp.previous;
    				
    			}
    			
    			if(nextY == start) {
    				temp.next = start;
    				temp.previous = null;
    				start.previous =temp;
    				start = temp;
    				size++;
    			}else {
    				temp.next = nextY;
    				temp.previous = nextY.previous;
    				nextY.previous.next = temp;
    				nextY.previous = temp;
    				size++;
    			}
    		}
    	}
    }
  
    /**
     * Check the PlayQueue for cycles.
     * <p>
     * There is at most one cycle in the PlayQueue. This may be bi-directional.
     * @return - true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle() {
    	
    	// traverse through the list and check if the next node is not a node we have visit previously
    	if(start != null) {
			HashSet<SongNode> songHash = new HashSet<>();
		
			System.out.println(middle.song.title);
			return hasDuplicateFowards(middle, songHash)|| hasDuplicateBackwards(middle.previous,songHash);
			
    	}
    
        return false;  // TODO: To be completed
    }
    public boolean hasDuplicateFowards (SongNode s, HashSet<SongNode> _songHash) {
    	
    	// if s is null;
    	if(s == null){
    		return false;
    	}
    	
    	if(_songHash.contains(s)) {
    		return true;
    	}else {
    		_songHash.add(s);
    	}

    	
    	return hasDuplicateFowards(s.next, _songHash);
    }
    public boolean hasDuplicateBackwards (SongNode s, HashSet<SongNode> _songHash) {
    	
    	// if s is null;
    	if(s == null){
    		return false;
    	}
    	
    	if(_songHash.contains(s)) {
    		return true;
    	}else {
    		_songHash.add(s);
    	}

    	
    	return hasDuplicateBackwards(s.previous,_songHash) ;
    }
    
    
    /**
     * Create and return a (semi) randomly shuffled PlayQueue from the calling object.
     * <p>
     * A shuffled PlayQueue begins with the same Song as the calling object.
     * For all other Songs in the resulting PlayQueue the following formula is used:
     * <p>
     * (x^2 + 1) % p * s % n
     * <p>
     * where x is the index previously taken from,
     * <p>
     * where p is a prime number,
     * <p>
     * where s is seed number.
     * <p>
     * and n is the length of the PlayQueue
     * <p>
     * You must ensure that you do not go out of bounds, and that when the provided formula
     * creates a cycle that it is no longer used. Then the Songs in all uncovered SongNodes
     * are added in their original order to the resulting PlayQueue.
     * 
     * @param p - prime number
     * @param s - seed number
     * @return the shuffled queue
     */
    public PlayQueue shuffledQueue(int p, int s) {
    	
    	PlayQueue shuffle = new PlayQueue();

    	if(start != null) {
    		if(this.size >= 1) {
    			SongNode temp = start;
    			int i = 0;
    			while(i < this.size) {
    				int formula = ((i^2)+ 1)% p * s % this.size;
    				shuffle.addSong(temp.song);
    				temp = temp.next;
    				i++;
    			}
    		}
    	}
        return shuffle;  // TODO: To be completed
    }


    @Override
    public String toString() {
        if (start == null) {
            return "null";
        }
        String forward = " forwards :         ";
        SongNode temp = start;
        while (temp.next != null) {
            forward += temp.song.title + " -> ";
            temp = temp.next;
        }
        forward += temp.song.title + " -> null";

        temp = end;
        String backward = "";
        while (temp.previous != null) {
            backward = " <- " + temp.song.title + backward;
            temp = temp.previous;
        }
        backward = "backwards : null <- " + temp.song.title + backward;
        return forward + "\n" + backward;
    }
}
