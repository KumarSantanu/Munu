package app.santanu.love.munu.ui.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import app.santanu.love.munu.R
import app.santanu.love.munu.data.model.Quotes

class QuotesAdapter(
    private val quotesList: List<Quotes>,
    private val onCopyClick: (String) -> Unit,
    private val onWhatsappClick: (String) -> Unit,
    private val onShareClick: (String) -> Unit
) : RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

    inner class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteText: AppCompatTextView = itemView.findViewById(R.id.actv_quotes_text)
        val btnCopy: AppCompatTextView = itemView.findViewById(R.id.actv_copy_quotes)
        val btnWaShare: AppCompatTextView = itemView.findViewById(R.id.actv_whatsapp_share_quotes)
        val btnShare: AppCompatTextView = itemView.findViewById(R.id.actv_share_quotes)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quotes, parent, false)
        return QuotesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: QuotesViewHolder,
        position: Int
    ) {
        val quote = quotesList[position]
        holder.quoteText.text = quote.quotes

        holder.btnCopy.setOnClickListener {
            onCopyClick(quote.quotes)
        }

        holder.btnWaShare.setOnClickListener {
            onWhatsappClick(quote.quotes)
        }

        holder.btnShare.setOnClickListener {
            onShareClick(quote.quotes)
        }
    }

    override fun getItemCount(): Int = quotesList.size
}