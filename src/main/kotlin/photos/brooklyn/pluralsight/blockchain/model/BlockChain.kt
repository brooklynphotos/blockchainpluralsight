package photos.brooklyn.pluralsight.blockchain.model

interface BlockChain {
    fun acceptBlock(block: Block)
    fun verifyChain()
}