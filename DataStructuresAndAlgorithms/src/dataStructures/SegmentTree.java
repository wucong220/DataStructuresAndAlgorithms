package dataStructures;

public class SegmentTree {
	
	public void build(SegNode[] segt,int[] num,int i,int left,int right){
		segt[i] = new SegNode();
		if(left==right) {
			segt[i].sum = num[left];
			return ;		
		}
		int mid = (left+right)>>1;
		build(segt,num, i*2,left,mid);
		build(segt,num, i*2+1,mid+1,right);
		segt[i].sum= segt[i*2].sum+segt[i*2+1].sum;
	}
	
	int rangeSumQuery(SegNode[] segt,int qlow,int qhigh,int i,int left,int right){
		if(qlow<=left&&qhigh>=right)return segt[i].sum;
		if(qlow>right||qhigh<left) return 0;
		int mid = (left+right)>>1;
		return rangeSumQuery(segt, qlow, qhigh, i*2+1, left, mid)+
				rangeSumQuery(segt, qlow, qhigh, i*2+2, mid+1, right);
	}
	
	int rangeSumQueryLazy(SegNode[] segt,int[] lazy,int qlow,int qhigh,int low,int high,int pos){
		if(qlow>qhigh)return 0;
		
		if(lazy[pos]!=0){
			segt[pos].sum+=lazy[pos];
			if(low!=high){
				lazy[2*pos + 1] += lazy[pos];
				lazy[2*pos + 2] += lazy[pos];
			}
			lazy[pos]=0;
		}
		
		if(qlow>high||qhigh<low) return 0;
		
		if(qlow<=low&&qhigh>=high)return segt[pos].sum;
		
		int mid = (low+high)>>1;
		return rangeSumQuery(segt, qlow, qhigh, low, mid, pos*2+1)+
				rangeSumQuery(segt, qlow, qhigh, mid+1, high, pos*2+2);
	}
	
	public void updateSegmentTreeRangeLazy(SegNode[] segt,int[] lazy,int startRange,int endRange,int delta,int low,int high,int pos){
		if(low>high)return;
		
		if(lazy[pos]!=0){
			segt[pos].sum+=lazy[pos];
			if(low!=high){
				lazy[2*pos + 1] += lazy[pos];
				lazy[2*pos + 2] += lazy[pos];
			}
			lazy[pos]=0;
		}
		
		//no overlap
		if(startRange>high||endRange<low){
			return;
		}
		
		//total overlap
		if(startRange <= low&& endRange >= high){
			segt[pos].sum += delta;
			if(low!=high){
				lazy[2*pos +1] += lazy[pos];
				lazy[2*pos +2] += lazy[pos];
			}
			return;
		}
		
		int mid = (low + high)/2;
		updateSegmentTreeRangeLazy(segt, lazy, startRange, endRange, delta, low, mid, pos*2+1);
		updateSegmentTreeRangeLazy(segt, lazy, startRange, endRange, delta, mid+1, high, pos*2+2);
	}

	
	public void update(SegNode[] segt,int i,int left,int right){
		
	}
}

class SegNode{
	int sum;
}
