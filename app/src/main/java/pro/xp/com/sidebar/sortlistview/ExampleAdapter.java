package pro.xp.com.sidebar.sortlistview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pro.xp.com.sidebar.R;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ContactsViewHolder> {

    private List<ExampleModel> contacts;

    public ExampleAdapter(List<ExampleModel> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        ExampleModel contact = contacts.get(position);
        holder.tvName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
