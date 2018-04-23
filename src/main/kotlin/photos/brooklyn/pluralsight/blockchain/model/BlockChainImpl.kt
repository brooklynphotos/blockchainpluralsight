package photos.brooklyn.pluralsight.blockchain.model


class BlockChainImpl: BlockChain {
    lateinit var current: Block
    var head: Block? = null
    private val blocks = mutableListOf<Block>()

    override fun acceptBlock(block: Block) {
        if (head == null)
        {
            head = block
        }

        current = block
        blocks.add(block)
    }

    override fun verifyChain() {
        val realHead = head ?: throw IllegalStateException("Genesis block not set.")

        val isValid = realHead.isValidChain(null, true)

        if (isValid) {
            println("Blockchain integrity intact.")
        } else {
            println("Blockchain integrity NOT intact.")
        }
    }
}