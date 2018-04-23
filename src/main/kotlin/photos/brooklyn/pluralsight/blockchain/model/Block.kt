package photos.brooklyn.pluralsight.blockchain.model

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*


class Block (
    private val blockNumber: Int,
    private val claimNumber: String,
    private val settlementAmount: Double,
    private val settlementDate: Date,
    private val carRegistration: String,
    private val mileage: Int,
    private val claimType: ClaimType,
    private val parent: Block? = null
){
    var createdDate: Date = Date()
    init {
        setBlockHash(parent)
    }
    private var blockHash: String? = null
    private var nextBlock: Block? = null
    private var previousBlockHash: String? = null

    fun calculateBlockHash(previousBlockHash: String?): String {
        val txnHash = claimNumber + settlementAmount + settlementDate + carRegistration + mileage + claimType
        val blockheader = blockNumber.toString() + createdDate.toString() + (previousBlockHash?:"")
        val combined = txnHash + blockheader

        val digest = MessageDigest.getInstance("SHA-256")
        val encodedHash = digest.digest(combined.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.getEncoder().encode(encodedHash))
    }

    private fun setBlockHash(parent: Block?){
        if (parent != null)
        {
            previousBlockHash = parent.blockHash
            parent.nextBlock = this
        }
        else
        {
            // Previous block is the genesis block.
            previousBlockHash = null
        }

        blockHash = calculateBlockHash(previousBlockHash);
    }
    fun isValidChain(prevBlockHash: String?, verbose: Boolean): Boolean {
        var isValid = true

        // Is this a valid block and transaction
        val newBlockHash = calculateBlockHash(prevBlockHash)
        isValid = (newBlockHash == blockHash) and (previousBlockHash == prevBlockHash)

        printVerificationMessage(verbose, isValid)

        // Check the next block by passing in our newly calculated blockhash. This will be compared to the previous
        // hash in the next block. They should match for the chain to be valid.

        return nextBlock?.isValidChain(newBlockHash, verbose) ?: isValid
    }

    private fun printVerificationMessage(verbose: Boolean, isValid: Boolean) {
        if (verbose) {
            if (!isValid) {
                println("Block Number $blockNumber : FAILED VERIFICATION")
            } else {
                println("Block Number $blockNumber : PASS VERIFICATION")
            }
        }
    }
}