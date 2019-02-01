//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.conor.softwareapp.R;
//import com.example.conor.softwareapp.music;
//
//import java.util.ArrayList;
//
//public class musicAdapter2 extends BaseAdapter {
//
//    private Context context;
//    private int layout;
//    private ArrayList arrayList;
//
//    public musicAdapter2(Context context, int layout, ArrayList arrayList) {
//        this.context = context;
//        this.layout = layout;
//        this.arrayList = arrayList;
//    }
//
//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//      return arrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    public class viewHolder{
//
//        TextView songName,artist;
//        ImageView play,stop;
//        private View convertView;
//        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        public viewHolder(){
//            convertView =layoutInflater.inflate(layout,null);
//            songName = convertView.findViewById(R.id.view1);
//            artist = convertView.findViewById(R.id.view2);
//            play = convertView.findViewById(R.id.view3);
//            stop = convertView.findViewById(R.id.view4);
//
//            play.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//
//
//
//        }
//
//
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//       final viewHolder  viewHolder = new viewHolder();
//        if(convertView == null){
//            convertView =viewHolder.convertView;
//
//        }else{
//
//
//        }
//
//       final Music mus = arrayList.get(position);
//        viewHolder.songName.setText(music);
//
//
//
//    }
//}
